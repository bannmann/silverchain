package silverchain.internal.middleware.graph.rewriter;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;
import silverchain.internal.middleware.graph.data.AttributeVisitor;
import silverchain.internal.middleware.graph.data.GraphListener;
import silverchain.internal.middleware.graph.data.attribute.Method;
import silverchain.internal.middleware.graph.data.attribute.TypeParameter;
import silverchain.internal.middleware.graph.data.attribute.TypeReference;
import silverchain.internal.middleware.graph.data.attribute.collection.TypeParameters;
import silverchain.internal.middleware.graph.data.graph.Edge;
import silverchain.internal.middleware.graph.data.graph.Graph;
import silverchain.internal.middleware.graph.data.graph.Node;

public class ParamRefResolver implements AttributeVisitor<Void, Void>, GraphListener {

  protected TypeParameters externalTypeParameters;

  protected TypeParameters internalTypeParameters;

  protected TypeParameters methodTypeParameters;

  @Override
  public void enter(Graph graph) {
    externalTypeParameters = graph.typeDeclaration().externalParameters();
    internalTypeParameters = graph.typeDeclaration().internalParameters();
    graph.typeDeclaration().accept(this, null);
  }

  @Override
  public void visit(Graph graph, Node source, Edge edge) {
    if (edge.label() != null) edge.label().accept(this, null);
  }

  @Override
  public Void visit(Method method, Void arg) {
    methodTypeParameters = method.typeParameters();
    AttributeVisitor.super.visit(method, arg);
    methodTypeParameters = null;
    return null;
  }

  @Override
  public Void visit(TypeReference typeReference, Void arg) {
    if (typeReference.name().qualifier() == null) typeReference.target(find(typeReference));
    AttributeVisitor.super.visit(typeReference, arg);
    return null;
  }

  protected TypeParameter find(TypeReference typeReference) {
    return Stream.of(methodTypeParameters, internalTypeParameters, externalTypeParameters)
        .filter(Objects::nonNull)
        .flatMap(Collection::stream)
        .filter(p -> p.name().equals(typeReference.name().id()))
        .findFirst()
        .orElse(null);
  }
}
