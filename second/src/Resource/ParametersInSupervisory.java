package Resource;

/**
 * Created by dovydas on 17.5.30.
 */
public class ParametersInSupervisory extends Resource {
  public ParametersInSupervisory() {
    setName(this.getClass().getName());
    setResourceState(ResourceState.FREE);
  }
}
