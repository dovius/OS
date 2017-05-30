package Process;

import Memory.ExternalMemory;
import OS.Kernel;
import OS.PhysicalMachine;
import Resource.ProgramInSupervisory;

/**
 * Created by dovydas on 17.5.29.
 */
public class Read extends Process {
  String supervisorMemory;
  public Read() {
    setPriority(99);
    setName(this.getClass().getName());
  }

  @Override
  public void execute(Kernel kernel) {
    switch (getStep()) {
      case 1:
        kernel.requestResources(this, "Resource.Stack", 1);
        increaseStep();
        break;
      case 2:
        PhysicalMachine.loadProgram("program.txt");
        increaseStep();
        break;
      case 3:
        kernel.requestResources(this, "Resource.SupervisorMemory", 1);
        increaseStep();
        break;
      case 4:
        supervisorMemory = ExternalMemory.read(PhysicalMachine.programs.get(0), 0);
        increaseStep();
        break;
      case 5:
        kernel.createResource(this, new ProgramInSupervisory());
        kernel.makeProcessBlocked(this);
        setStep(1);
        break;
    }
  }
}
