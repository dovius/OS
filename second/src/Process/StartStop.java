package Process;

import Resource.InputResource;
import OS.*;
import Resource.MOSEnd;

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
        kernel.createResource(this, new MOSEnd());
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
      case 3:
        kernel.requestResources(this, "Resource.MOSEnd", 1);
        increaseStep();
        break;
      case 4:
        //process delete
        increaseStep();
        break;
      case 5:
        //resources delete
        increaseStep();

        //TERMINATE RM
        break;
    }
  }
}
