package Process;

/**
 * Created by dovydas on 17.5.22.
 */
public class StartStopProcess extends Process {

  public StartStopProcess() {
    setPriority(100);
    setName("StartStop");
  }

  @Override
  public void execute() {
    switch (getStep()) {
      case 1:
        break;
      case 2:
        break;
    }
  }
}
