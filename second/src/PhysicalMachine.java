import java.io.*;
import java.util.ArrayList;

/**
 * Created by dovydas on 17.3.8.
 */
public class PhysicalMachine {
  private CPU cpu;
  private ExternalMemory externalMemory;
  private RealMemory realMemory;
  public static ArrayList<Integer> programs = new ArrayList<>();

  public static byte mode;
  public static int sp;
  public static int pc;
  public static int r;
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
    realMemory = new RealMemory(16);
    setRegisters();
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
      // todo change second parameter to dynamic
      programs.add(program.length() * 2);
      externalMemory.write(program.toCharArray(), 0);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String readProgram(int offset) {
    String result = new String();
    return result;
  }

  public static void resolveCommand(String line, VirtualMachine vm) throws Exception {
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
    } else if (line.equals("PUSH")) {
      vm.PUSH();
    } else if (line.equals("POP")) {
      vm.POP();
    } else if (line.equals("PRNL")) {
      vm.PRNL();
    } else if (line.substring(0, 2).equals("GD")) {
      vm.GD(line.substring(2, 3), line.substring(3, 4));
    } else if (line.substring(0, 2).equals("PD")) {
      vm.PD(line.substring(2, 3), line.substring(3, 4));
    }
  }

  public void run() {
    loadProgram("program.txt");
    ExternalMemory.read(programs.get(0), 0);
    VirtualMachine virtualMachine = new VirtualMachine();

    try {
      virtualMachine.fillMemory();
      mode = 1;
    } catch (IOException e) {
      System.out.println("error filling VM memory");
    }

    showMemory(virtualMachine, 0);
    showMemory(virtualMachine, 1);

    String com;
    while (!(com = getCommand(virtualMachine)).equals("HALT") && virtualMachine.pc < 15) {
      try {
        PhysicalMachine.resolveCommand(com, virtualMachine);
      } catch (Exception e) {
        System.out.println("error executing VM commands");
      }
    }

    System.out.println("***AFTER PROGRAM EXECUTION***");
    showMemory(virtualMachine, 0);
    showMemory(virtualMachine, 1);
  }

  public String getCommand(VirtualMachine vm) {
    return vm.memory.getBlock(1).get(++vm.pc);
  }

  public void showMemory(VirtualMachine vm, int block) {
    System.out.println("BLOCK - " + block);
    for (int i = 0; i < 16; i++) {
      System.out.println("cell " + i + " -> " + vm.memory.getBlock(block).getWord(i).getValue());
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
}
