package silverchain.internal.middle.graph.ir.attribute;

import java.util.stream.Stream;
import silverchain.internal.middle.graph.ir.AttributeVisitor;

public class Name implements Attribute {

  private String id;

  private Name qualifier;

  public String id() {
    return id;
  }

  public void id(String id) {
    this.id = id;
  }

  public Name qualifier() {
    return qualifier;
  }

  public void qualifier(Name qualifier) {
    this.qualifier = qualifier;
  }

  @Override
  public Stream<? extends Attribute> children() {
    return Stream.of(qualifier);
  }

  @Override
  public <R, A> R accept(AttributeVisitor<R, A> visitor, A arg) {
    return visitor.visit(this, arg);
  }
}
