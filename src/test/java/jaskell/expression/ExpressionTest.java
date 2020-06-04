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
    Parsec<Expression, Character> p = new Parser();
    Expression expression = p.parse(state);
    Assert.assertEquals(3.14, expression.eval(), 0.00001);
  }

  @Test
  public void testAdd() throws EOFException {
    State<Character> state = new TxtState("3.14+2.53");
    Parsec<Expression, Character> p = new Parser();
    Expression expression = p.parse(state);
    Assert.assertEquals(5.67, expression.eval(), 0.00001);
  }

  @Test
  public void testSub() throws EOFException {
    State<Character> state = new TxtState("179- 8");
    Parsec<Expression, Character> p = new Parser();
    Expression expression = p.parse(state);
    Assert.assertEquals(171, expression.eval(), 0.00001);
  }

  @Test
  public void testProduct() throws EOFException {
    State<Character> state = new TxtState("8 * -8");
    Parsec<Expression, Character> p = new Parser();
    Expression expression = p.parse(state);
    Assert.assertEquals(-64, expression.eval(), 0.00001);
  }

  @Test
  public void testDivide() throws EOFException {
    State<Character> state = new TxtState("128/8");
    Parsec<Expression, Character> p = new Parser();
    Expression expression = p.parse(state);
    Assert.assertEquals(16, expression.eval(), 0.00001);
  }

  @Test
  public void testPriorities() throws EOFException {
    State<Character> state = new TxtState("7 + 15 * 3");
    Parsec<Expression, Character> p = new Parser();
    Expression expression = p.parse(state);
    expression = expression.makeAst();
    Assert.assertEquals(52, expression.eval(), 0.00001);
  }

  @Test
  public void testPrioritiesFlow() throws EOFException {
    State<Character> state = new TxtState("5 * 3 + 7");
    Parsec<Expression, Character> p = new Parser();
    Expression expression = p.parse(state);
    expression = expression.makeAst();
    Assert.assertEquals(22, expression.eval(), 0.00001);
  }

  @Test
  public void testPloyQuote() throws EOFException {
    State<Character> state = new TxtState("5 * (3 + 7)");
    Parsec<Expression, Character> p = new Parser();
    Expression expression = p.parse(state);
    expression = expression.makeAst();
    Assert.assertEquals(50, expression.eval(), 0.00001);
  }

  @Test
  public void testPloyComplex() throws EOFException {
    State<Character> state = new TxtState("5 * (3 + 7) -22.5");
    Parsec<Expression, Character> p = new Parser();
    Expression expression = p.parse(state);
    expression = expression.makeAst();
    Assert.assertEquals(27.5, expression.eval(), 0.00001);
  }

  @Test
  public void testPloyMoreComplex() throws EOFException {
    State<Character> state = new TxtState("5 * (3 + 7) - -22.5");
    Parsec<Expression, Character> p = new Parser();
    Expression expression = p.parse(state);
    expression = expression.makeAst();
    Assert.assertEquals(72.5, expression.eval(), 0.00001);
  }
}
