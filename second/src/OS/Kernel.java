package OS;

import Process.Process;
import Process.State;
import Resource.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by dovydas on 17.5.22.
 */

public class Kernel {
  private List<Resource> resources = new ArrayList<>();
  private List<Process> allProcesses = new ArrayList<>();
  private PriorityQueue<Process> blockedProcesses = new PriorityQueue<>();
  private PriorityQueue<Process> readyProcesses = new PriorityQueue<>();
  private Process currentProcess;

  public void init() {
  }

  public void run(){
    Process process = this.getCurrentProcess();
    if(process == null){
      System.out.println("no processes are running");
    }
    else{
      process.execute(this);
    }
  }


  public void createProcess(Process parentProcess, Process createdProcess) {
    System.out.println("process " + createdProcess.getName() + " created");
    allProcesses.add(createdProcess);
    createdProcess.setState(State.READY);
    readyProcesses.add(createdProcess);
    //add to ready processes
    if (parentProcess != null) {
      parentProcess.getChild().add(createdProcess);
      createdProcess.setParent(parentProcess);
    }

    //setCurrentProcess(createdProcess);
    //add child?
  }

  public void createResource(Process owner, Resource resource) {
    System.out.println("resource " + resource.getName() + " created");
    resource.setCreator(owner);
    owner.addResource(resource);
    resources.add(resource);
  }

  public void runProcess(Process process) {
    System.out.println("running process " + process.getName());
    process.setState(State.RUN);
    process.execute(this);
  }

  public void planner() {
    Process firstReady = readyProcesses.peek();
    //is current process blocked?
    if (currentProcess != null && currentProcess.getState() == State.BLOCK) {
      blockedProcesses.add(currentProcess);
      currentProcess = null;
    }
    if(currentProcess == null){
      if(firstReady == null){
        System.out.println("all processes is blocked");
      }
      else{
        System.out.println("planner is changing processes");
        firstReady = readyProcesses.poll();
        // really?
        currentProcess = firstReady;
        runProcess(firstReady);
      }
    }
    else{
      if(firstReady == null){
        System.out.println("No other ready processes, starting current one");
      }
      else if(firstReady.getPriority() > currentProcess.getPriority()){
        System.out.println("Current process has lower priority");
        firstReady = readyProcesses.poll();
        runProcess(firstReady);
        currentProcess.setState(State.READY);
        readyProcesses.add(currentProcess);
      }
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

  public PriorityQueue<Process> getBlockedProcesses() {
    return blockedProcesses;
  }

  public void setBlockedProcesses(PriorityQueue<Process> blockedProcesses) {
    this.blockedProcesses = blockedProcesses;
  }

  public Process getCurrentProcess() {
    return currentProcess;
  }

  public void setCurrentProcess(Process currentProcess) {
    this.currentProcess = currentProcess;
  }
}
