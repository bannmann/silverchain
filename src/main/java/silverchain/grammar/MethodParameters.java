package silverchain.grammar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class MethodParameters extends ASTNodeN<MethodParameter, MethodParameters> {

  public MethodParameters(MethodParameter head, MethodParameters tail) {
    super(head, tail);
  }

  public List<TypeParameter> referents() {
    return StreamSupport.stream(spliterator(), false)
        .map(MethodParameter::referents)
        .flatMap(Collection::stream)
        .distinct()
        .collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  String separator() {
    return ",";
  }
}
