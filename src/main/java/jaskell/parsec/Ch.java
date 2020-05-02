package jaskell.parsec;

/**
 * Created by Mars Liu on 2016-01-07.
 * Ch 即 char, 是为 Character 特化的 Eq.
 */
public class Ch<Status, Tran>
    extends Eq<Character, Status, Tran> implements Parsec<Character, Character, Status, Tran>{
    public Ch(Character chr) {
        super(chr);
    }
}
