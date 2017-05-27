package Process;

/**
 * Created by dovydas on 17.5.22.
 */
public class Loader extends Process {
  public Loader() {
    setName(this.getClass().getName());
    setPriority(96);
  }
}
