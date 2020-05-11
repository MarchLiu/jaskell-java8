package jaskell.parsec;

import java.util.List;

/**
 * Created by Mars Liu on 16/9/12.
 * Text Parsec Utils
 */
public class Txt {
    public static <Status, Tran> Ch<Status, Tran> ch(char value) {
        return new Ch<>(value);
    }

    public static <Status, Tran> Ch<Status, Tran> ch(char value, boolean caseSensitive) {
        return new Ch<>(value, caseSensitive);
    }

    public static <Status, Tran> ChIn<Status, Tran> chIn(String data) {
        return new ChIn<>(data);
    }

    public static <Status, Tran> ChIn<Status, Tran> chIn(String data, boolean caseSensitive) {
        return new ChIn<>(data, caseSensitive);
    }

    public static <Status, Tran> ChNone<Status, Tran> chNone(String data) {
        return new ChNone<>(data);
    }

    public static <Status, Tran> ChNone<Status, Tran> chNone(String data, boolean caseSensitive) {
        return new ChNone<>(data, caseSensitive);
    }

    public static <Status, Tran> Crlf<Status, Tran> crlf() {
        return new Crlf<>();
    }

    public static <Status, Tran> Decimal<Status, Tran> decimal() {
        return new Decimal<>();
    }

    public static <Status, Tran> UDecimal<Status, Tran> udecimal() {
        return new UDecimal<>();
    }

    public static <Status, Tran> Digit<Status, Tran> digit() {
        return new Digit<>();
    }

    public static <Status, Tran> Int<Status, Tran> integer() {
        return new Int<>();
    }

    public static <Status, Tran> UInt<Status, Tran> uinteger() {
        return new UInt<>();
    }

    public static <Status, Tran> EndOfLine<Status, Tran> eol() {
        return new EndOfLine<>();
    }

    public static <Status, Tran> Newline<Status, Tran> newline() {
        return new Newline<>();
    }

    public static <Status, Tran> Space<Status, Tran> space() {
        return new Space<>();
    }

    public static <Status, Tran> Whitespace<Status, Tran> whitespace() {
        return new Whitespace<>();
    }

    public static <Status, Tran> NoWhitespace<Status, Tran> noWhitespace() {
        return new NoWhitespace<>();
    }

    public static <Status, Tran> SkipSpaces<Status, Tran> skipSpaces() {
        return new SkipSpaces<>();
    }

    public static <Status, Tran> Whitespace<Status, Tran> skipWhiteSpaces() {
        return new Whitespace<>();
    }

    public static <Status, Tran> Text<Status, Tran> text(String value) {
        return new Text<>(value);
    }

    public static <Status, Tran> Text<Status, Tran> text(String value, boolean caseSensitive) {
        return new Text<>(value, caseSensitive);
    }

    public static <Status, Tran> JoinText<Status, Tran> joining() {
        return new JoinText<>();
    }

    public static <Status, Tran> JoinCharacters<Status, Tran> joinChars() {
        return new JoinCharacters<>();
    }
}
