package Process;

import OS.Kernel;

/**
 * Created by dovydas on 17.5.22.
 */
public class Main extends Process{
  public Main() {
    setName(this.getClass().getName());
    //dont know
    setPriority(97);
  }

  @Override
  public void execute(Kernel kernel) {
    switch (getStep()) {
      case 1:
        kernel.requestResources(this, "Resource.ProgramInSupervisory", 1);
        increaseStep();
        break;
      case 2:
        if (kernel.getResource("Resource.ProgramInSupervisory").getExecutionTime() == 0) {
          kernel.destroyProcess(this.getChild().get(0));
        }
        else {
          kernel.createProcess(this, new JobGovernor());
        }
        increaseStep();
        break;
      case 3:
        setStep(1);
        kernel.requestResources(this, "Resource.ProgramInSupervisory", 1);
        break;
    }
  }
}
