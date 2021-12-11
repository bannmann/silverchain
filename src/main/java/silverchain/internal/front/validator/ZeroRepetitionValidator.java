package silverchain.internal.front.validator;

import silverchain.internal.front.parser.AgBaseListener;
import silverchain.internal.front.parser.AgParser.RepeatSugarContext;

public class ZeroRepetitionValidator extends AgBaseListener {

  @Override
  public void enterRepeatSugar(RepeatSugarContext ctx) {
    if (ctx.MAX != null && Integer.parseInt(ctx.MAX.getText()) == 0) {
      throw new ZeroRepetition(ctx);
    }
  }
}