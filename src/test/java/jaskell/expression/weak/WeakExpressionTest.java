package jaskell.expression.weak;

import jaskell.expression.Env;
import jaskell.expression.Expression;
import jaskell.expression.ExpressionException;
import jaskell.expression.parser.N;
import jaskell.expression.weak.parser.WeakParser;
import jaskell.parsec.common.Parsec;
import jaskell.parsec.common.State;
import jaskell.parsec.common.TxtState;
import org.junit.Assert;
import org.junit.Test;

import java.io.EOFException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/10 17:03
 */
public class WeakExpressionTest {
  private final WeakParser parser = new WeakParser();
  private final Env emptyEnv = new Env();

  @Test
  public void testNumber() throws Throwable {
    State<Character> state = new TxtState("3.14");
    Parsec<Character, Expression> p = new N();
    Expression expression = p.parse(state);
    Assert.assertEquals(3.14, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testBasic() throws Throwable {
    State<Character> state = new TxtState("3.14<6.18");
    Expression expression = parser.parse(state);
    Assert.assertEquals(1, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testGreat() throws Throwable {
    State<Character> state = new TxtState("3>1");
    Expression expression = parser.parse(state);
    Assert.assertEquals(1, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testSubMixed() throws Throwable {
    State<Character> state = new TxtState("179- (8>5)");
    Expression expression = parser.parse(state);
    expression = expression.makeAst();
    Assert.assertEquals(178, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testProductMixed() throws Throwable {
    State<Character> state = new TxtState("8 * (1<5<3) - 8");
    Expression expression = parser.parse(state);
    expression = expression.makeAst();
    Assert.assertEquals(-8, expression.eval(emptyEnv), 0.00001);
  }

}
