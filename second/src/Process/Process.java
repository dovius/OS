package Process;

import Resource.Resource;
import OS.Kernel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dovydas on 17.5.22.
 */
public abstract class Process implements Comparable {

  private String name;   //pid
  private State state;   //st
  private String father_process;   //parent process PPID
  private String ptt;
  private int priority;
  private List<Resource> resources;
  private List<Process> child;
  private Process parent;
  private List<Resource> waitingResources;
  private int step = 1;

  protected static Kernel kernel;

  public Process() {
    resources = new ArrayList<>();
    child = new ArrayList<>();
    waitingResources = new ArrayList<>();
  }

  public void execute(Kernel kernel) {
  }


  public List<Resource> getWaitingResources() {
    return waitingResources;
  }

  public void setWaitingResources(List<Resource> waitingResources) {
    this.waitingResources = waitingResources;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public String getFather_process() {
    return father_process;
  }

  public void setFather_process(String father_process) {
    this.father_process = father_process;
  }

  public String getPtt() {
    return ptt;
  }

  public void setPtt(String ptt) {
    this.ptt = ptt;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public int getStep() {
    return step;
  }

  public void increaseStep() {
    this.step++;
  }

  public void setStep(int step) {
    this.step = step;
  }

  public List<Resource> getResources() {
    return resources;
  }

  public void addResource(Resource resource) {
    this.resources.add(resource);
  }

  public List<Process> getChild() {
    return child;
  }

  public void setChild(List<Process> child) {
    this.child = child;
  }

  public Process getParent() {
    return parent;
  }

  public void setParent(Process parent) {
    this.parent = parent;
  }

  public void removeChild(Process p) {
    child.remove(p);
  }

  public void destroyChildren() {
    for (int i = 0; i < child.size(); ++i) {
      kernel.destroyProcess(child.get(i));
    }
  }

  public void destroyResources() {
    for (int i = 0; i < resources.size(); ++i) {
      //kernel.deleteResource(this, ownedResources.get(i));
    }
  }

  //  public void releaseResource(Resource resource){
//    this.ownedResources.remove(resource);
//  }
//
//
  public void releaseResources() {
    for (Resource resource : resources) {
      kernel.freeResource(this, resource);
    }
  }

  public void addWaitingResource(Resource resource) {
    this.waitingResources.add(resource);
  }

  public int compareTo(Object o) {
    return ((Integer) (((Process) o).priority)).compareTo(((Integer) this.priority));
  }

}
