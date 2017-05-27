package Process;

/**
 * Created by dovydas on 17.5.22.
 */
public class Write extends Process {
  public Write() {
    setName(this.getClass().getName());
    setPriority(80);
  }
}
