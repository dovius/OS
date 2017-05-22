import Process.Process;
import Process.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dovydas on 17.5.22.
 */

public class Kernel {
  private List<Resource> resources = new ArrayList<>();
  private List<Process> allProcesses = new ArrayList<>();
  private List<Process> blockedProcesses = new ArrayList<>();
  private Process currentProcess;

  public void init() {

  }

  public void createProcess(Process parentProcess, Process currentProcess) {
    System.out.println("process " + currentProcess.getName() + " created");
    currentProcess.setState(State.READY);
    allProcesses.add(currentProcess);
    //add child?
  }

  public void runProcess(Process process) {
    System.out.println("running process " + process.getName());
    process.setState(State.RUN);
    process.increaseStep();
    process.execute();
  }

  public void planner() {
    if (currentProcess != null && currentProcess.getState() == State.BLOCK) {
      currentProcess.setState(State.BLOCK);
      //need this?
      blockedProcesses.add(currentProcess);
    }
    Process readyProcess = readyProcessExists();
    if (readyProcess != null) {
      runProcess(readyProcess);
    }
    else {
      System.out.println("no more ready processes...");
    }
  }

  public Process readyProcessExists() {
    Process readyProcess = null;
    int maxPriority = -1;
    for (Process process : allProcesses) {
      if (process.getState() == State.READY && process.getPriority() >= maxPriority) {
        readyProcess = process;
        maxPriority = process.getPriority();
      }
    }
    return readyProcess;
  }

  public List<Resource> getResources() {
    return resources;
  }

  public void setResources(List<Resource> resources) {
    this.resources = resources;
  }

  public List<Process> getAllProcesses() {
    return allProcesses;
  }

  public void setAllProcesses(List<Process> allProcesses) {
    this.allProcesses = allProcesses;
  }

  public List<Process> getBlockedProcesses() {
    return blockedProcesses;
  }

  public void setBlockedProcesses(List<Process> blockedProcesses) {
    this.blockedProcesses = blockedProcesses;
  }

  public Process getCurrentProcess() {
    return currentProcess;
  }

  public void setCurrentProcess(Process currentProcess) {
    this.currentProcess = currentProcess;
  }
}
