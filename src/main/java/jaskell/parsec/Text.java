package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * text 是特化的文本匹配算子,它期待后续的数据流是一个匹配给定字符串的字符序列,否则抛出异常.
 */
public class Text<Status, Tran>
    implements Parsec<Character, String, Status, Tran>{
    private final String text;
    private final Boolean caseSensitive;

    public Text(String text) {
        this(text, true);
    }

    public Text(String text, boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
        if(caseSensitive){
            this.text = text;
        } else {
            this.text = text.toLowerCase();
        }
    }

    @Override
    public String parse(State<Character, Status, Tran> s)
            throws EOFException, ParsecException {
        int idx = 0;
        for(Character c: this.text.toCharArray()) {
            Character data = s.next();
            if (caseSensitive) {
                if (c != data) {
                    String message = String.format("Expect %c at %d but %c", c, idx, data);
                    throw s.trap(message);
                }
            } else {
                if (c != data.toString().toLowerCase().charAt(0)) {
                    String message = String.format("Expect %c at %d but %c", c, idx, data);
                    throw s.trap(message);
                }
            }
            idx ++;
        }
        return text;
    }

}
