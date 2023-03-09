package jaskell.parsec.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OneTest extends Base {

  /**
   * Method: script(State<T> s)
   */
  @Test
  public void testOne() throws Exception {
    State<Character> state = newState("hello");

    One<Character> one = new One<>();


    Character c = one.parse(state);
    assertEquals('h', (char) c);
  }

} 
