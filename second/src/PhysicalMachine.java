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
 // public static char ptr;
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
  public static byte[] sf = {0,0,0,0}; //0-OF, 1-SF, 2-ZF, 3-CF

  public PhysicalMachine() {
    cpu = new CPU();
    externalMemory = new ExternalMemory();
    realMemory = new RealMemory(16);
  }
  public static void setRegisters(){
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
  public void loadProgram(String fileName) {
    try (FileReader fr = new FileReader(fileName);
         BufferedReader br = new BufferedReader(fr)) {

      int c;
      String program = "";
      int charCount = 0;
      while ((c = br.read()) != -1) {
        if (c > 20 && c < 150) {
          charCount++;
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
  public static void  resolveCommand(String line) throws  Exception {
    if (line.substring(0, 3).equals("ADD")) {
      VirtualMachine.ADD();
    } else if (line.substring(0, 3).equals("SUB")) {
      VirtualMachine.SUB();
    } else if (line.substring(0, 3).equals("MUL")) {
      VirtualMachine.MUL();
    } else if (line.substring(0, 3).equals("DIV")) {
      VirtualMachine.DIV();
    } else if (line.substring(0, 3).equals("CMP")) {
      VirtualMachine.CMP();
    } else if (line.substring(0, 2).equals("JM")) {
      VirtualMachine.JM(line.substring(2, 4));
    } else if (line.substring(0, 2).equals("JE")) {
      VirtualMachine.JE(line.substring(2, 4));
    } else if (line.substring(0, 2).equals("JG")) {
      VirtualMachine.JG(line.substring(2, 4));
    } else if (line.substring(0, 2).equals("JL")) {
      VirtualMachine.JL(line.substring(2, 4));
    } else if (line.substring(0, 2).equals("JA")) {
      VirtualMachine.JA(line.substring(2, 4));
    } else if (line.substring(0, 2).equals("JB")) {
      VirtualMachine.JB(line.substring(2, 4));
    } else if (line.substring(0, 2).equals("JN")) {
      VirtualMachine.JN(line.substring(2, 4));
    } else if (line.equals("PUSH")) {
      VirtualMachine.PUSH();
    } else if (line.equals("POP")) {
      VirtualMachine.POP();
    } else if (line.equals("PRNL")) {
      VirtualMachine.PRNL();
    } else if (line.substring(0, 2).equals("GD")) {
      VirtualMachine.GD(line.substring(2, 3), line.substring(3, 4));
    } else if (line.substring(0, 2).equals("PD")) {
      VirtualMachine.PD(line.substring(2, 3), line.substring(3, 4));
    }
  }

  public void run() {
    loadProgram("program.txt");
    String program = ExternalMemory.read(programs.get(0), 0);
    VirtualMachine virtualMachine = new VirtualMachine();
    try {
      virtualMachine.fillMemory();
      mode = 1;
    } catch (IOException e) {
      System.out.println("error filling VM memory");
    }
  }
  public static void setOF(){
        sf[0] = 1;
  }
  public static void setSF(){
    sf[1] = 1;
  }
  public static void setZF(){
    sf[2] = 1;
  }
  public static void setCF(){
    sf[3] = 1;
  }

  public static byte getOF(){
    return sf[0];
  }
  public static byte getSF(){
    return sf[1];
  }
  public static byte getZF(){
    return sf[2];
  }
  public static byte getCF(){
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
 public static void clearZF(){
   sf[2] = 0;
 }
}
