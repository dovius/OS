package Process;

import Resource.Resource;
import OS.Kernel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dovydas on 17.5.22.
 */
public abstract class Process implements Comparable{

  private String name;   //pid
  private State state;   //st
  private String father_process;   //parent process PPID
  private String ptt;
  private int priority;
  private List<Resource> resources;

  private List<Process> child;
  private Process parent;
  private int step = 1;

  public Process() {
    resources = new ArrayList<>();
    child = new ArrayList<>();
  }

  public void execute(Kernel kernel) {
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

  public int compareTo(Object o) {
    return ((Integer)(((Process) o).priority)).compareTo(((Integer)this.priority));
  }

}
