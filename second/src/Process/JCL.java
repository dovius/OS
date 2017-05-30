package Process;

import OS.Kernel;
import Resource.ParametersInSupervisory;

/**
 * Created by dovydas on 17.5.22.
 */
public class JCL extends Process {
  public JCL() {
    setName(this.getClass().getName());
    setPriority(98);
  }

  @Override
  public void execute(Kernel kernel) {
    switch (getStep()) {
      case 1:
        kernel.requestResources(this, "Resource.ProgramInSupervisory", 1);
        increaseStep();
        break;
      case 2:
        increaseStep();
        break;
      case 3:
        increaseStep();
        break;
      case 4:
        increaseStep();
        break;
      case 5:
        kernel.createResource(this, new ParametersInSupervisory());
        setStep(1);
        kernel.makeProcessBlocked(this);
        break;
    }
  }
}

