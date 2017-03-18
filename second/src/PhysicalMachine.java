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

  public void run() {
    String command = "LO";
    while (true) {
      switch (command) {
        case "LO":

          break;
        case "STEP":

          break;
      }
      VirtualMachine virtualMachine = new VirtualMachine();
    }
  }
}
