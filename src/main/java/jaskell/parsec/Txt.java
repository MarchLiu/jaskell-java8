package jaskell.parsec;

import java.util.List;

/**
 * Created by Mars Liu on 16/9/12.
 * Text Parsec Utils
 */
public class Txt {
    public static Parsec<Character, Character> ch(char value) {
        return new Ch(value);
    }

    public static Parsec<Character, Character> chIn(String data) {
        return new ChIn(data);
    }

    public static Parsec<Character, Character> chNone(String data) {
        return new ChNone(data);
    }

    public static Parsec<String, Character> crlf() {
        return new Crlf();
    }

    public static Parsec<String, Character> decimal() {
        return new Decimal();
    }

    public static Parsec<String, Character> udecimal() {
        return new UDecimal();
    }

    public static Parsec<Character, Character> digit() {
        return new Digit();
    }

    public static Parsec<String, Character> integer() {
        return new Int();
    }

    public static Parsec<String, Character> uinteger() {
        return new UInt();
    }

    public static Parsec<String, Character> eol() {
        return new EndOfLine();
    }

    public static Parsec<Character, Character> newline() {
        return new Newline();
    }

    public static Parsec<Character, Character> space() {
        return new Space();
    }

    public static Parsec<Character, Character> whitespace() {
        return new Whitespace();
    }

    public static Parsec<Character, Character> skipSpaces() {
        return new SkipSpaces();
    }

    public static Parsec<Character, Character> skipWhiteSpaces() {
        return new Whitespace();
    }

    public static <Status, Tran> Parsec<String, Character> text(String value) {
        return new Text(value);
    }
    
    public static <E> Binder<List<E>, String, E> joining() {
        return new JoinText<>();
    }

    public static <E> Binder<List<Character>, String, Character> joinChars() {
        return new JoinCharacters();
    }
}
