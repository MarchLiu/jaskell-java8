package jaskell.expression;

import jaskell.expression.parser.N;
import jaskell.expression.parser.Parser;
import jaskell.parsec.common.Parsec;
import jaskell.parsec.common.State;
import jaskell.parsec.common.TxtState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/04 12:01
 */
public class ExpressionTest {
  private final Parser parser = new Parser();
  private final Env emptyEnv = new Env();

  @Test
  public void testNumber() throws Exception {
    State<Character> state = new TxtState("3.14");
    Parsec<Character, Expression> p = new N();
    Expression expression = p.parse(state);
    assertEquals(3.14, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testBasic() throws Exception {
    State<Character> state = new TxtState("3.14");
    Expression expression = parser.parse(state);
    assertEquals(3.14, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testAdd() throws Exception {
    State<Character> state = new TxtState("3.14+2.53");
    Expression expression = parser.parse(state);
    assertEquals(5.67, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testSub() throws Exception {
    State<Character> state = new TxtState("179- 8");
    Expression expression = parser.parse(state);
    assertEquals(171, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testProduct() throws Exception {
    State<Character> state = new TxtState("8 * -8");
    Expression expression = parser.parse(state);
    assertEquals(-64, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testDivide() throws Exception {
    State<Character> state = new TxtState("128/8");
    Expression expression = parser.parse(state);
    assertEquals(16, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testPriorities() throws Exception {
    State<Character> state = new TxtState("7 + 15 * 3");
    Expression expression = parser.parse(state);
    expression = expression.makeAst();
    assertEquals(52, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testPrioritiesFlow() throws Exception {
    State<Character> state = new TxtState("5 * 3 + 7");
    Expression expression = parser.parse(state);
    expression = expression.makeAst();
    assertEquals(22, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testPloyQuote() throws Exception {
    String content = "5 * (3 + 7)";
    Expression expression = parser.parse(content);
    expression = expression.makeAst();
    assertEquals(50, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testPloyComplex() throws Exception {
    State<Character> state = new TxtState("5 * (3 + 7) -22.5");
    Expression expression = parser.parse(state);
    expression = expression.makeAst();
    assertEquals(27.5, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testPloyMoreComplex() throws Exception {
    String content = "5 * (3 + 7) - -22.5";
    Expression expression = parser.parse(content);
    expression = expression.makeAst();
    assertEquals(72.5, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testPloyScientific() throws Exception {
    String content = "5 * (3 + 7e2) - -22.5";
    Expression expression = parser.parse(content);
    expression = expression.makeAst();
    assertEquals(3537.5, expression.eval(emptyEnv), 0.00001);

    content = "5 * (3E-3 + 7) - -22.5e8";
    expression = parser.parse(content);
    expression = expression.makeAst();
    assertEquals(2.250000035015E9, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testNormalExpression() throws Exception {
    String content = "3.14 + 7 * 8 - (2 + 3)";
    Expression expression = parser.parse(content);
    expression = expression.makeAst();
    assertEquals(54.14, expression.eval(emptyEnv), 0.00001);
  }

  @Test
  public void testParameterExpression() throws Exception {
    Env env = new Env();
    env.put("p0", 15d);
    String content = "3.14 + 7 * 8 - (2 + p0)";
    Expression expression = parser.parse(content);
    expression = expression.makeAst();
    assertEquals(42.14, expression.eval(env), 0.00001);
  }
}
