package silverchain.internal.middle.graph.data.attribute.collection;

import silverchain.internal.middle.graph.data.AttributeVisitor;
import silverchain.internal.middle.graph.data.attribute.TypeParameter;

public class TypeParameters extends Attributes<TypeParameter> {

  @Override
  public <R, A> R accept(AttributeVisitor<R, A> visitor, A arg) {
    return visitor.visit(this, arg);
  }
}