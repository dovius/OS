import java.io.*;

/**
 * Created by dovydas on 17.3.8.
 */
public class PhysicalMachine {
  private CPU cpu;
  private ExternalMemory externalMemory;

  public static byte mode;
  public static char ptr;
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
  public static byte sd;

  public PhysicalMachine() {
    cpu = new CPU();
    externalMemory = new ExternalMemory();
  }

  public void readProgram(String fileName) {
    try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();


      while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
      }
      String everything = sb.toString();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  public void run() {
    readProgram("program.txt");



//    String command = "LO";
//    while (true) {
//      switch (command) {
//        case "LO":
//
//          break;
//        case "STEP":
//
//          break;
//      }
      VirtualMachine virtualMachine = new VirtualMachine();
    }
}
