package jaskell.parsec;

import static jaskell.parsec.common.Atom.eof;
import static jaskell.parsec.common.Atom.one;
import static jaskell.parsec.common.Combinator.ahead;
import static jaskell.parsec.common.Combinator.attempt;
import static jaskell.parsec.common.Combinator.between;
import static jaskell.parsec.common.Combinator.choice;
import static jaskell.parsec.common.Combinator.many;
import static jaskell.parsec.common.Combinator.many1;
import static jaskell.parsec.common.Txt.ch;
import static jaskell.parsec.common.Txt.joining;
import static jaskell.parsec.common.Txt.nCh;

import jaskell.parsec.common.Parsec;
import org.junit.Assert;
import org.junit.Test;

import java.io.EOFException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/19 22:40
 */
public class InjectionTest {
  private final Parsec<Character, Character> escapeChar = ch('\\').then(s -> {
    Character c = s.next();
    switch (c) {
      case 't':
        return '\t';
      case 'n':
        return '\n';
      case 'r':
        return '\r';
      case '\'':
        return '\'';
      default:
        throw s.trap(String.format("invalid escape char %c", c));
    }
  });

  Parsec<Character, Character> e = eof();

  Parsec<Character, Character> notEof = ahead(one());

  Parsec<Character, Character> oneChar = choice(attempt(escapeChar), nCh('\''));

  Parsec<Character, String> stringContent = between(ch('\''), ch('\''), many(oneChar)).bind(joining());

  Parsec<Character, String> noString = many1(nCh('\'')).bind(joining());

  Parsec<Character, String> content = choice(attempt(noString), stringContent);

  Parsec<Character, String> parser = many(notEof.then(content)).bind(values -> s -> {
    e.parse(s);
    return String.join("", values);
  });

  @Test
  public void testBasic() throws Throwable {
    String content = "a data without text content";
    Assert.assertEquals(content, parser.parse(content));
  }

  @Test
  public void testEscape() throws Throwable {
    String content = "'a data with text content'";
    Assert.assertEquals("a data with text content", parser.parse(content));
  }

  @Test
  public void testEscapeMore() throws Throwable {
    String content = "some content included 'a data without\ttext content'";
    Assert.assertEquals("some content included a data without\ttext content", parser.parse(content));
  }

  @Test
  public void testInjection() throws Throwable {
    String content = "some content included 'a data without 'text content'";
    Assert.assertTrue(parser.exec(content).isErr());
  }


}
