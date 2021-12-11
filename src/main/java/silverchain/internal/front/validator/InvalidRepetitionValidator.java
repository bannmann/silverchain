package silverchain.internal.front.validator;

import org.antlr.v4.runtime.Token;
import silverchain.internal.front.parser.AgBaseListener;
import silverchain.internal.front.parser.AgParser.RepeatSugarContext;

public class InvalidRepetitionValidator extends AgBaseListener {

  @Override
  public void enterRepeatSugar(RepeatSugarContext ctx) {
    Token min = ctx.MIN;
    Token max = ctx.MAX;
    if (min != null && max != null) {
      if (Integer.parseInt(min.getText()) > Integer.parseInt(max.getText())) {
        throw new InvalidRepetition(ctx);
      }
    }
  }
}