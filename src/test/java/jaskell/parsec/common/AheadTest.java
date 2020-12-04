package jaskell.parsec.common;

import static jaskell.parsec.common.Combinator.ahead;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import jaskell.parsec.ParsecException;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;
import static jaskell.parsec.common.Txt.*;

/**
 * Created by Mars Liu on 16/9/15.
 *
 * Tests About look behind.
 */
public class AheadTest extends Base {

    @Test
    public void simple() throws Exception{
        State<Character> state = newState("this is a string data.");
        Parsec<Character, String> parser =
                text("this").over(new Ahead<>(text(" is")));

        String re = parser.parse(state);

        assertEquals(re, "this");
        assertThat("Expect status stop after this but is. ", state.status(), IsEqual.equalTo(4));
    }

    @Test
    public void result() throws Exception{
        State<Character> state = newState("this is a string data.");
        Parsec<Character, String> parser =
                text("this").then(space()).then(new Ahead<>(text("is")));

        String re = parser.parse(state);

        assertEquals(re, "is");
        assertThat("Expect status stop after this (5) but is. ", state.status(), IsEqual.equalTo(5));
    }

    @Test
    public void fail() throws Exception{
        State<Character> state = newState("this is a string.");
        Parsec<Character, String> parser =
                text("this").then(space()).then(ahead(text(" is")));

        try {
            String re = parser.parse(state);
        } catch (Exception e) {
            assertThat("Expect status stop after this (5) but is. ", state.status(), IsEqual.equalTo(5));
            assertThat("Expect the parser fail when try match \" is\"",
                    e, IsInstanceOf.instanceOf(ParsecException.class));
        }
    }
}
