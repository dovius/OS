package Process;

/**
 * Created by dovydas on 17.5.22.
 */
public class JCL extends Process {
  public JCL() {
    setName(this.getClass().getName());
    setPriority(65);
  }
}

