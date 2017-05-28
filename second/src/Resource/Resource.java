package Resource;

import Process.Process;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by dovydas on 17.5.22.
 */
public class Resource {
  String name;
  private int RID;
  private String type;
  private int PID;
  private ResourceState resourceState;
  private Process creator;
  private Map<Process, Integer> waitingProcesses = new HashMap<>();

  public Resource() {
    setResourceState(ResourceState.BLOCKED);
  }

  public Map<Process, Integer> getWaitingProcesses() {
    return waitingProcesses;
  }

//  public void addWaitingProcesses(Process waitingProcesses) {
//    waitingProcesses.add(waitingProcesses);
//  }

  public Process getCreator() {
    return creator;
  }

  public void setCreator(Process creator) {
    this.creator = creator;
  }

  public int getRID() {
    return RID;
  }

  public void setRID(int RID) {
    this.RID = RID;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getPID() {
    return PID;
  }

  public void setPID(int PID) {
    this.PID = PID;
  }

  public ResourceState getResourceState() {
    return resourceState;
  }

  public void setResourceState(ResourceState resourceState) {
    this.resourceState = resourceState;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
