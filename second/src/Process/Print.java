package Process;

/**
 * Created by dovydas on 17.5.22.
 */
public class Print extends Process {
  public Print() {
    setName(this.getClass().getName());
    setPriority(75);
  }
}
