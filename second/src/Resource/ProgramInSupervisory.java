package Resource;

/**
 * Created by dovydas on 17.5.30.
 */
public class ProgramInSupervisory extends Resource {
  public ProgramInSupervisory() {
    setName(this.getClass().getName());
    setResourceState(ResourceState.FREE);
  }
}
