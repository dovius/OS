package Resource;

/**
 * Created by dovydas on 17.5.29.
 */
public class Stack extends Resource {
  public Stack() {
    setName(this.getClass().getName());
    setResourceState(ResourceState.FREE);
  }
}
