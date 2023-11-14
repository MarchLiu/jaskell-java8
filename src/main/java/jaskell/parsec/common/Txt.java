package jaskell.parsec.common;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mars Liu on 16/9/12.
 * Text Parsec Utils
 */
public class Txt {
    public static Ch ch(char value) {
        return new Ch(value);
    }

    public static Ch ch(char value, boolean caseSensitive) {
        return new Ch(value, caseSensitive);
    }

    public static NCh nCh(char value) {
        return new NCh(value);
    }

    public static NCh nCh(char value, boolean caseSensitive) {
        return new NCh(value, caseSensitive);
    }


    public static ChIn chIn(String data) {
        return new ChIn(data);
    }

    public static ChIn chIn(String data, boolean caseSensitive) {
        return new ChIn(data, caseSensitive);
    }

    public static ChNone chNone(String data) {
        return new ChNone(data);
    }

    public static ChNone chNone(String data, boolean caseSensitive) {
        return new ChNone(data, caseSensitive);
    }

    public static Crlf crlf() {
        return new Crlf();
    }

    public static Decimal decimal() {
        return new Decimal();
    }

    public static UDecimal udecimal() {
        return new UDecimal();
    }

    public static Digit digit() {
        return new Digit();
    }

    public static Int integer() {
        return new Int();
    }

    public static UInt uinteger() {
        return new UInt();
    }

    public static EndOfLine eol() {
        return new EndOfLine();
    }

    public static Newline newline() {
        return new Newline();
    }

    public static Space space() {
        return new Space();
    }

    public static Whitespace whitespace() {
        return new Whitespace();
    }

    public static NoWhitespace noWhitespace() {
        return new NoWhitespace();
    }

    public static SkipSpaces skipSpaces() {
        return new SkipSpaces();
    }

    public static SkipWhitespaces skipWhiteSpaces() {
        return new SkipWhitespaces();
    }

    public static Skip1Spaces skip1Spaces() {
        return new Skip1Spaces();
    }

    public static Skip1Whitespaces skip1WhiteSpaces() {
        return new Skip1Whitespaces();
    }

    public static Text text(String value) {
        return new Text(value);
    }

    public static Text text(String value, boolean caseSensitive) {
        return new Text(value, caseSensitive);
    }

    public static Enumerate<Character, Character> enumerate(String enumerates) {
        List<Parsec<Character, Character>> parsers = enumerates.chars()
                .mapToObj(c -> (char) c)
                .map(Txt::ch)
                .collect(Collectors.toList());
        return new Enumerate<>(parsers);
    }

    public static Enumerate<Character, Character> enumerate(String enumerates, String sep) {
        Parsec<Character, String> p = new Text(sep);
        return enumerate(enumerates).by(p);
    }

    public static Enumerate<Character, Character> enumerate(String enumerates, char sep) {
        return enumerate(enumerates).by(ch(sep));
    }

    public static Enumerate<Character, Character> enumerate(String enumerates, Parsec<Character, ?> sep) {
        return enumerate(enumerates).by(sep);
    }

    public static JoinText joining() {
        return new JoinText();
    }

    public static JoinCharacters joinChars() {
        return new JoinCharacters();
    }

    public static Letter letter() {
        return new Letter();
    }
}
