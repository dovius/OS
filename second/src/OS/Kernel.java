package OS;

import Process.Process;
import Process.State;
import Resource.Resource;
import Resource.ResourceState;

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
    System.out.println(createdProcess.getName() + " created");
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

  public void destroyProcess(Process p){
    System.out.println("Destroying process: " + p.getName());
    if(p.getParent() != null){
      p.getParent().removeChild(p);
    }

    p.destroyChildren();
    p.releaseResources();
    p.destroyResources();

    allProcesses.remove(p);
    readyProcesses.remove(p);
    blockedProcesses.remove(p);

    System.out.println("Finished destroying process: " + p);
  }

  public void createResource(Process owner, Resource resource) {
    System.out.println(resource.getName() + " created");
    resource.setCreator(owner);
    owner.addResource(resource);
    resources.add(resource);
  }

  public void runProcess(Process process) {
    System.out.println("running process " + process.getName());
    process.setState(State.RUN);
    process.execute(this);
  }

  public void processPlanner() {
    while (true) {
      if (currentProcess != null && currentProcess.getState() != State.BLOCK) {
        readyProcesses.add(currentProcess);
      }
      if (readyProcesses.peek() != null) {
        currentProcess = readyProcesses.poll();
        runProcess(currentProcess);
      } else {
        System.out.println("no mo ready processes. sleeping..");
      }
    }
  }

  public void resourceDistributor(Process askingProc, String requestedResource, int amount ) {
    navigateForResource(askingProc, requestedResource);
    for (Process process : blockedProcesses) {
      navigateForResource(process, requestedResource);
    }
    processPlanner();
  }

  //todo refactor name. Resource size..
  private void navigateForResource(Process askingProc, String requestedResource) {
    for (Resource resource : askingProc.getWaitingResources()) {
      if (ResourceState.FREE == resource.getResourceState() && resource.getName().equalsIgnoreCase(requestedResource)) {
        blockedProcesses.remove(askingProc);
        askingProc.setState(State.READY);
        readyProcesses.add(askingProc);
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

  public void requestResources(Process askingProc, String requestedResource, int amount){
    System.out.println("Process: " + askingProc.getName() + " requested " + amount + " of resource: " + requestedResource);
    Resource resource = getResource(requestedResource);
    makeProcessBlocked(askingProc);
    askingProc.addWaitingResource(resource);
    if (resource != null) {
      resource.getWaitingProcesses().put(askingProc, amount);
    }
    resourceDistributor(askingProc, requestedResource, amount);
  }

  public Resource getResource(String resExtName){
    for (Resource resource : resources) {
      if (resource.getName().equalsIgnoreCase(resExtName)) {
        return resource;
      }
    }
    System.out.println("resource " + resExtName + " is not found");
    return null;
  }

  public void freeResource(Process process, Resource resource){
    System.out.println("Freeing resource: " + resource + " ...");
//    Resource r = getResource(resource.getrIDI());
//    if(r != null){
//      process.releaseResource(resource);
//      //??
//      processPlanner();
//    }
//    Logger.log("Finished freeing resource: " + resource);
  }

//  public Process getHighestPriorityProcess() {
//    int priority = 0;
//    Process highestProcess = null;
//    for (Process process : readyProcesses) {
//      if (process.getPriority() >= priority) {
//        priority = process.getPriority();
//        highestProcess = process;
//      }
//    }
//    return highestProcess;
//  }

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

  public void makeProcessBlocked(Process process) {
    process.setState(State.BLOCK);
    blockedProcesses.add(process);
  }
}
