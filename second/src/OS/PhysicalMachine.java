package OS;

import Memory.*;
import Process.StartStop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by dovydas on 17.3.8.
 */
public class PhysicalMachine {
  public static int MAX_REAL_MEMORY_BLOCKS = 16;
  private CPU cpu;
  private ExternalMemory externalMemory;
  public MemoryBlock[] realMemory;
  public Paging paging;
  public Kernel kernel;
  public static ArrayList<Integer> programs = new ArrayList<>();
  public static byte mode;
  public static int sp;
  public static int pc;
  public static int r;
  public static int ptr;
  public static byte ti;
  public static byte si;
  public static byte pi;
  public static byte ioi;
  public static byte ch1;
  public static byte ch2;
  public static byte ch3;
  public static byte[] sf = {0, 0, 0, 0}; //0-OF, 1-SF, 2-ZF, 3-CF

  public PhysicalMachine() {
    cpu = new CPU();
    externalMemory = new ExternalMemory();
    //utils
    paging = new Paging();
    kernel = new Kernel();
    setRegisters();
    initRealMemory();
  }

  public void run() {
    try {
      kernel.createProcess(null, new StartStop());
      kernel.planner();
      kernel.run();

//      loadProgram("program.txt");
//      ExternalMemory.read(programs.get(0), 0);
//      VirtualMachine virtualMachine = new VirtualMachine();
//      ptr = paging.getFreeBlock(realMemory);
//      realMemory[ptr] = paging.getPageTable(realMemory);
//      virtualMachine.fillMemory(ptr);
//      mode = 1;
//
//      System.out.println("### VM memory filled");
//      showMemory(virtualMachine);
//      showRegisters(virtualMachine);
//
//      System.out.println("### VM started program");
//      String com;
//      while (!(com = getCommand(virtualMachine)).equals("HALT")) {
//        resolveCommand(com, virtualMachine);
//        syncMemory(virtualMachine);
//        System.out.println("command executed: " + com);
//        showMemory(virtualMachine);
//        showMemory(realMemory);
//        showRegisters(virtualMachine);
//        if (Main.stepMode) {
//          System.in.read();
//        }
//      }
//
//      System.out.println("### Vm executed program");
//      showMemory(virtualMachine, 0);
//      showMemory(virtualMachine, 1);
//      showRegisters(virtualMachine);
//      freeMemory(virtualMachine, 2);
    } catch (Exception e) {
      System.out.println("Error executing VM commands ");
    }
  }


  public void loadProgram(String fileName) {
    try (FileReader fr = new FileReader(fileName);
         BufferedReader br = new BufferedReader(fr)) {
      int c;
      String program = "";
      while ((c = br.read()) != -1) {
        if (c > 20 && c < 150) {
          program += (char) c;
        }
      }
      programs.add(program.length() * 2);
      externalMemory.write(program.toCharArray(), 0);
    } catch (IOException e) {
      System.out.println(" load program error");
    }
  }

  public String resolveCommand(String line, VirtualMachine vm) throws Exception {
    if (line.substring(0, 3).equals("ADD")) {
      vm.ADD();
    } else if (line.substring(0, 3).equals("SUB")) {
      vm.SUB();
    } else if (line.substring(0, 3).equals("MUL")) {
      vm.MUL();
    } else if (line.substring(0, 3).equals("DIV")) {
      vm.DIV();
    } else if (line.substring(0, 3).equals("CMP")) {
      vm.CMP();
    } else if (line.substring(0, 2).equals("JM")) {
      vm.JM(line.substring(2, 4));
    } else if (line.substring(0, 2).equals("JE")) {
      vm.JE(line.substring(2, 4));
    } else if (line.substring(0, 2).equals("JG")) {
      vm.JG(line.substring(2, 4));
    } else if (line.substring(0, 2).equals("JL")) {
      vm.JL(line.substring(2, 4));
    } else if (line.substring(0, 2).equals("JA")) {
      vm.JA(line.substring(2, 4));
    } else if (line.substring(0, 2).equals("JB")) {
      vm.JB(line.substring(2, 4));
    } else if (line.substring(0, 2).equals("JN")) {
      vm.JN(line.substring(2, 4));
    } else if (line.substring(0, 2).equals("PS")) {
      vm.PS(line.substring(2, 4));
    } else if (line.equals("PUSH")) {
      vm.PUSH();
    } else if (line.equals("POP")) {
      vm.POP();
    } else if (line.equals("PRNL")) {
      vm.PRNL();
    } else if (line.substring(0, 2).equals("GD")) {
      si = 1;
    } else if (line.substring(0, 2).equals("PD")) {
      si = 2;
    } else if (line.substring(0, 2).equals("PP")) {
      vm.PP(line.substring(2, 4));
    }
    if (test()) {
      handleInterrupt(vm, line);
    }

    ti++;
    return null;
  }

  private static boolean test() {
    return si > 0 || pi > 0 || ti == 0;
  }

  public String getCommand(VirtualMachine vm) {
    return vm.memory.getCode(vm.pc++);
  }

  public void freeMemory(VirtualMachine vm, int blocks) {
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 16; j++) {
        vm.memory.getBlock(i).setWord(j, new Word("   "));
      }
    }
  }

  public void showRegisters(VirtualMachine vm) {
    System.out.print("VM sp:" + vm.sp + " pc:" + pc + "     " + "PM SF:" + getOF() + getSF() + getZF() + getCF());
    System.out.print('\n');
  }

  public void showMemory(VirtualMachine vm, int block) {
    System.out.print("BLOCK" + block + " ");
    for (int i = 0; i < 16; i++) {
      System.out.print(i + ":" + vm.memory.getBlock(block).getWord(i).getValue() + " ");
    }
    System.out.print('\n');
  }

  public void showMemory(VirtualMachine vm) {
    System.out.println("---- VM ----");
    for (int blockIter = 0; blockIter < 8; blockIter++) {
      System.out.print("BLOCK" + blockIter + " ");
      for (int i = 0; i < 16; i++) {
        System.out.print(i + ":" + vm.memory.getBlock(blockIter).getWord(i).getValue() + " ");
      }
      System.out.print('\n');
    }
    System.out.println("------------");
  }

  public void showMemory(MemoryBlock[] realMemory) {
    System.out.println("---- RM ----");
    for (int blockIter = 0; blockIter < RealMemory.MAX_MEMORY_BLOCKS; blockIter++) {
      System.out.print("BLOCK" + blockIter + " ");
      for (int i = 0; i < 16; i++) {
        System.out.print(i + ":" + realMemory[blockIter].getWord(i).getValue() + " ");
      }
      System.out.print('\n');
    }
    System.out.println("------------");
  }

  public String resolveInterrupts(String interrupt, String data) {
    if (interrupt.equals("PD")) {
      System.out.println(data);
    }
    if (interrupt.equals("GD")) {
      Scanner reader = new Scanner(System.in);  // Reading from System.in
      return reader.nextLine();
    }
    return null;
  }


  public void handleInterrupt(VirtualMachine vm, String line) {
    if (si == 2) {
      String data = vm.PD(line.substring(2, 3), line.substring(3, 4));
      resolveInterrupts(line.substring(0, 2), data);
      System.out.println();
    }
    else if (si == 1) {
      String data = null;
      vm.GD(line.substring(2, 3), line.substring(3, 4));
      data = resolveInterrupts(line.substring(0, 2), data);
    }
  }

  public static void setRegisters() {
    sp = 0;
    ch1 = 0;
    ch2 = 0;
    ch3 = 0;
    r = 0;
    mode = 0;
    ti = 1;
    si = 0;
    pi = 0;
    ioi = 0;
  }

  public static void setOF() {
    sf[0] = 1;
  }

  public static void setSF() {
    sf[1] = 1;
  }

  public static void setZF() {
    sf[2] = 1;
  }

  public static void setCF() {
    sf[3] = 1;
  }

  public static byte getOF() {
    return sf[0];
  }

  public static byte getSF() {
    return sf[1];
  }

  public static byte getZF() {
    return sf[2];
  }

  public static byte getCF() {
    return sf[3];
  }

  /* public static void clearOF(){
     sf[0] = 0;
   }
   public static void clearSF(){
     sf[1] = 0;
   }

   public static void clearCF(){
     sf[3] = 0;
   }*/
  public static void clearZF() {
    sf[2] = 0;
  }

  public void initRealMemory() {
    realMemory = new MemoryBlock[MAX_REAL_MEMORY_BLOCKS];
    for (int i = 0; i < MAX_REAL_MEMORY_BLOCKS; i++) {
      realMemory[i] = new MemoryBlock();
    }
  }

  public void syncMemory(VirtualMachine vm) {
    MemoryBlock pageTable = realMemory[vm.ptr];
    for (int i = 0; i < VirtualMemory.MAX_VIRTUAL_MEMORY_BLOCKS; i++) {
      realMemory[pageTable.getWord(i).getIntValue()] = vm.memory.getBlock(i);
    }
  }
}
