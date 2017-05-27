package Resource;
import Process.Process;


/**
 * Created by dovydas on 17.5.22.
 */
public class Resource {
  String name;
  private int RID;
  private String type;
  private int PID;
  private State state;
  private Process creator;

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

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


}
