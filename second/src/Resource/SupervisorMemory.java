package Resource;

/**
 * Created by dovydas on 17.5.29.
 */
public class SupervisorMemory extends Resource {
  public SupervisorMemory() {
    setName(this.getClass().getName());
    setResourceState(ResourceState.FREE);
  }
}
