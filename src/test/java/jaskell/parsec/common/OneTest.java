package jaskell.parsec.common;

import jaskell.parsec.ParsecException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OneTest extends Base {

  @Before
  public void before() throws Exception {
  }

  @After
  public void after() throws Exception {
  }

  /**
   * Method: script(State<T> s)
   */
  @Test
  public void testOne() throws Exception {
    State<Character> state = newState("hello");

    One<Character> one = new One<>();


    Character c = one.parse(state);
    Assert.assertEquals('h', (char) c);
  }

} 
