package jaskell.parsec.common;

import java.util.List;

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

    public static Whitespace skipWhiteSpaces() {
        return new Whitespace();
    }

    public static Text text(String value) {
        return new Text(value);
    }

    public static Text text(String value, boolean caseSensitive) {
        return new Text(value, caseSensitive);
    }

    public static JoinText joining() {
        return new JoinText();
    }

    public static JoinCharacters joinChars() {
        return new JoinCharacters();
    }
}
