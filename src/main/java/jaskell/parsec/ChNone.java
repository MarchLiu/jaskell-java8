package jaskell.parsec;

import java.io.EOFException;
import java.util.Set;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toSet;

/**
 * Created by Mars Liu on 2016-01-10.
 * ChNone 即 char none of,是为 Character 特化的 none of
 */
public class ChNone<Status, Tran>
    implements Parsec<Character, Character, Status, Tran> {
    private final Set<Character> chars;
    private final Boolean caseSensitive;

    @Override
    public Character parse(State<Character, Status, Tran> s)
        throws EOFException, ParsecException {
        Character c = s.next();
        if(caseSensitive){
            if(!chars.contains(c)){
                return c;
            }
        }else{
            if(!chars.contains(c.toString().toLowerCase().charAt(0))){
                return c;
            }
        }

        throw s.trap(String.format("expect any char none of %s (case sensitive %b) but get %c",
            chars, caseSensitive, c));
    }

    public ChNone(String data){
        this(data, false);
    }

    public ChNone(String data, boolean caseSensitive){
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
