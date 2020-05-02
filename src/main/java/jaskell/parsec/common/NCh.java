package jaskell.parsec.common;

/**
 * Created by Mars Liu on 2016/9/28.
 * Expect State return a char not equals chr.
 */
public class NCh extends Ne<Character>
    implements Parsec<Character, Character> {
    public NCh(Character chr) {
        super(chr);
    }

}
