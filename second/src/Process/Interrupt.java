package Process;

/**
 * Created by dovydas on 17.5.22.
 */
public class Interrupt extends Process {
  public Interrupt() {
    setName(this.getClass().getName());
    setPriority(97);
  }
}
