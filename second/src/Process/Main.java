package Process;

/**
 * Created by dovydas on 17.5.22.
 */
public class Main extends Process{
  public Main() {
    setName(this.getClass().getName());
    setPriority(99);
  }

}
