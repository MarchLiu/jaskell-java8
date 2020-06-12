package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/12 19:26
 */
public class Letter implements Parsec<Character, Character> {
  @Override
  public Character parse(State<Character> s) throws EOFException, ParsecException {
    Character c = s.next();
    if(Character.isLetter(c)){
      return c;
    } else {
      throw s.trap(String.format("expect a letter but get %c", c));
    }
  }
}
