package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016/9/28.
 * Expect State return a char not equals chr.
 */
public class NCh<Status, Tran> implements Parsec<Character, Character, Status, Tran>{
    final private Character chr;
    final  private boolean caseSensitive;
    public NCh(Character chr) {
        this.chr = chr;
        caseSensitive = true;
    }

    public NCh(Character chr, Boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
        if(caseSensitive){
            this.chr = chr;
        }else {
            this.chr = chr.toString().toLowerCase().charAt(0);
        }
    }

    @Override
    public Character parse(State<Character, Status, Tran> s) throws EOFException, ParsecException {
        Character c = s.next();
        if (caseSensitive){
            if(!chr.equals(c)){
                return c;
            }
        } else {
            Character lc = c.toString().toLowerCase().charAt(0);
            if(!chr.equals(lc)){
                return c;
            }
        }
        throw s.trap(String.format("expect any char is not %c (case sensitive %b) at %s but %c",
            chr, caseSensitive, s.status().toString(), c));
    }
}
