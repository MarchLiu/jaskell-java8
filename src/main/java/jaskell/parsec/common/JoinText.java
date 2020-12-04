package jaskell.parsec.common;

import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.stream.Collector;

/**
 * Created by Mars Liu on 16/9/13.
 * JoinText is a binder. It join Character List to String.
 */
public class JoinText
    implements Binder<Character, List<Character>, String> {
  private final String sep;
  @Override
  public Parsec<Character, String> bind(List<Character> value) {
    Collector<CharSequence, ?, String> j = sep == null ? joining() : joining(sep);
    return state -> value.stream().map(Object::toString).collect(j);
  }

  public JoinText() {
    sep = null;
  }

  public JoinText(String sep) {
    this.sep = sep;
  }
}

