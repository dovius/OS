package Process;

/**
 * Created by dovydas on 17.5.22.
 */
public class VirtualMachine extends Process {
  public VirtualMachine() {
    setName(this.getClass().getName());
    setPriority(50);
  }
}
