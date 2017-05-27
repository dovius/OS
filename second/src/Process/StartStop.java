package Process;

import Resource.InputResource;
import OS.*;

/**
 * Created by dovydas on 17.5.22.
 */
public class StartStop extends Process {

  public StartStop() {
    setPriority(100);
    setName(this.getClass().getName());
  }

  @Override
  public void execute(Kernel kernel) {
    switch (getStep()) {
      case 1:
        kernel.createResource(this, new InputResource());
        increaseStep();
        break;
      case 2:
        kernel.createProcess(this, new Main());
        kernel.createProcess(this, new Interrupt());
        kernel.createProcess(this, new JCL());
        kernel.createProcess(this, new JobGovernor());
        kernel.createProcess(this, new Loader());
        kernel.createProcess(this, new Main());
        kernel.createProcess(this, new VirtualMachine());
        kernel.createProcess(this, new Write());
        increaseStep();
        break;
    }
  }
}
