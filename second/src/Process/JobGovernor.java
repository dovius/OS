package Process;

/**
 * Created by dovydas on 17.5.22.
 */
public class JobGovernor extends Process{
  public JobGovernor() {
    setName(this.getClass().getName());
    setPriority(98);
  }
}
