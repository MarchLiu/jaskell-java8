package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * Ch 即 char, 是为 Character 特化的 Eq.
 */
public class Ch implements Parsec<Character, Character> {
    private final Character chr;
    private final Boolean caseSensitive;

    public Ch(Character chr) {
        this(chr, true);
    }

    public Ch(Character chr, Boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
        if(caseSensitive) {
            this.chr = chr;
        }else{
            this.chr = chr.toString().toLowerCase().charAt(0);
        }
    }

    @Override
    public Character parse(State<Character> s) throws EOFException, ParsecException {
        Character c = s.next();
        if(caseSensitive){
            if(c.equals(chr)){
                return c;
            }
        }else{
            if(chr.equals(c.toString().toLowerCase().charAt(0))){
                return c;
            }
        }
        throw s.trap(String.format("expect char %c (case sensitive %b) at %s but %c",
            chr, caseSensitive, s.status().toString(), c));
    }
}
