package jaskell.parsec;

/**
 * Created by Mars Liu on 2016/9/28.
 * Expect State return a char not equals chr.
 */
public class NCh<Status, Tran> extends Ne<Character, Status, Tran>
    implements Parsec<Character, Character, Status, Tran>{
    public NCh(Character chr) {
        super(chr);
    }
}
