import java.io.*;

/**
 * Created by dovydas on 17.3.8.
 */
public class PhysicalMachine {
  private CPU cpu;
  private ExternalMemory externalMemory;

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
