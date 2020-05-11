package jaskell.parsec;

import java.io.EOFException;
import java.util.Set;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toSet;

/**
 * Created by Mars Liu on 2016-01-10.
 * ChIn 即 char in ,是为 Character 特化的 one of
 */
public class ChIn<Status, Tran> implements
    Parsec<Character, Character, Status, Tran> {
    private final Set<Character> chars;
    private final Boolean caseSensitive;

    @Override
    public Character parse(State<Character, Status, Tran> s)
            throws EOFException, ParsecException {
        Character c = s.next();
        if(caseSensitive){
            if(chars.contains(c)){
                return c;
            }
        }else{
            if(chars.contains(c.toString().toLowerCase().charAt(0))){
                return c;
            }
        }

        throw s.trap(String.format("expect any char in %s (case sensitive %b) but get %c", chars, caseSensitive, c));
    }

    public ChIn(String data){
        this(data, false);
    }

    public ChIn(String data, boolean caseSensitive){
        this.caseSensitive = caseSensitive;
        if(caseSensitive) {
            this.chars = IntStream.range(0, data.length())
                .mapToObj(data::charAt).collect(toSet());
        } else {
            String content = data.toLowerCase();
            this.chars = IntStream.range(0, content.length())
                .mapToObj(content::charAt).collect(toSet());
        }

    }

}
