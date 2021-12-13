package silverchain.internal.middleware.graph.data.attribute.collection;

import org.apiguardian.api.API;
import silverchain.internal.middleware.graph.data.AttributeVisitor;
import silverchain.internal.middleware.graph.data.attribute.Parameter;

@API(status = API.Status.INTERNAL)
public class Parameters extends Attributes<Parameter> {

  @Override
  public <R, A> R accept(AttributeVisitor<R, A> visitor, A arg) {
    return visitor.visit(this, arg);
  }
}
