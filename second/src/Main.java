public class Main {
  public static boolean stepMode = false;

  public static void main(String[] args) {
    if (args.length > 0 && args[0].equals("stepmode")) {
      stepMode = true;
    }
    PhysicalMachine physicalMachine = new PhysicalMachine();
    physicalMachine.run();
  }
}
