package jaskell.parsec;

/**
 * Created by Mars Liu on 2016-01-07.
 * Ch 即 char, 是为 Character 特化的 Eq.
 */
public class Ch extends Eq<Character> implements Parsec<Character, Character>{
    public Ch(Character chr) {
        super(chr);
    }
}
