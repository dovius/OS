package Process;

/**
 * Created by dovydas on 17.5.22.
 */
public abstract class Process {

  private String name;   //pid
  private State state;  //st
  private String father_process;   //parent process PPID
  private String ptt;
  private int priority;


  private int step = 0;

  public Process() {
  }

  public void execute() {
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
}
