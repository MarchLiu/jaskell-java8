package jaskell.expression;

import jaskell.expression.parser.N;
import jaskell.expression.parser.Parser;
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
 * @since 2020/06/04 12:01
 */
public class ExpressionTest {
  private final Parser parser = new Parser();
  @Test
  public void testNumber() throws EOFException {
    State<Character> state = new TxtState("3.14");
    Parsec<Expression, Character> p = new N();
    Expression expression = p.parse(state);
    Assert.assertEquals(3.14, expression.eval(), 0.00001);
  }

  @Test
  public void testBasic() throws EOFException {
    State<Character> state = new TxtState("3.14");
    Expression expression = parser.parse(state);
    Assert.assertEquals(3.14, expression.eval(), 0.00001);
  }

  @Test
  public void testAdd() throws EOFException {
    State<Character> state = new TxtState("3.14+2.53");
    Expression expression = parser.parse(state);
    Assert.assertEquals(5.67, expression.eval(), 0.00001);
  }

  @Test
  public void testSub() throws EOFException {
    State<Character> state = new TxtState("179- 8");
    Expression expression = parser.parse(state);
    Assert.assertEquals(171, expression.eval(), 0.00001);
  }

  @Test
  public void testProduct() throws EOFException {
    State<Character> state = new TxtState("8 * -8");
    Expression expression = parser.parse(state);
    Assert.assertEquals(-64, expression.eval(), 0.00001);
  }

  @Test
  public void testDivide() throws EOFException {
    State<Character> state = new TxtState("128/8");
    Expression expression = parser.parse(state);
    Assert.assertEquals(16, expression.eval(), 0.00001);
  }

  @Test
  public void testPriorities() throws EOFException {
    State<Character> state = new TxtState("7 + 15 * 3");
    Expression expression = parser.parse(state);
    expression = expression.makeAst();
    Assert.assertEquals(52, expression.eval(), 0.00001);
  }

  @Test
  public void testPrioritiesFlow() throws EOFException {
    State<Character> state = new TxtState("5 * 3 + 7");
    Expression expression = parser.parse(state);
    expression = expression.makeAst();
    Assert.assertEquals(22, expression.eval(), 0.00001);
  }

  @Test
  public void testPloyQuote() throws EOFException {
    String content = "5 * (3 + 7)";
    Expression expression = parser.parse(content);
    expression = expression.makeAst();
    Assert.assertEquals(50, expression.eval(), 0.00001);
  }

  @Test
  public void testPloyComplex() throws EOFException {
    State<Character> state = new TxtState("5 * (3 + 7) -22.5");
    Expression expression = parser.parse(state);
    expression = expression.makeAst();
    Assert.assertEquals(27.5, expression.eval(), 0.00001);
  }

  @Test
  public void testPloyMoreComplex() throws EOFException {
    String content = "5 * (3 + 7) - -22.5";
    Expression expression = parser.parse(content);
    expression = expression.makeAst();
    Assert.assertEquals(72.5, expression.eval(), 0.00001);
  }

  @Test
  public void testPloyScientific() throws EOFException {
    String content = "5 * (3 + 7e2) - -22.5";
    Expression expression = parser.parse(content);
    expression = expression.makeAst();
    Assert.assertEquals(3537.5, expression.eval(), 0.00001);

    content = "5 * (3E-3 + 7) - -22.5e8";
    expression = parser.parse(content);
    expression = expression.makeAst();
    Assert.assertEquals(2.250000035015E9, expression.eval(), 0.00001);
  }

  @Test
  public void testNormalExpression() throws EOFException {
    String content = "3.14 + 7 * 8 - (2 + 3)";
    Expression expression = parser.parse(content);
    expression = expression.makeAst();
    Assert.assertEquals(54.14, expression.eval(), 0.00001);
  }
}
