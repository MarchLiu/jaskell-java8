package jaskell.parsec;

import static jaskell.parsec.Txt.space;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

/**
 * Created by Mars Liu on 16/9/15.
 *
 * Tests About look behind.
 */
public class AheadTest extends Base {

    @Test
    public void simple() throws Exception{
        State<Character, Integer, Integer> state = newState("this is a string data.");
        Parsec<String, Character> parser =
                new Text("this").over(new Ahead<>(new Text(" is")));

        String re = parser.parse(state);

        assertEquals(re, "this");
        assertThat("Expect status stop after this but is. ", state.status(), IsEqual.equalTo(4));
    }

    @Test
    public void result() throws Exception{
        State<Character, Integer, Integer> state = newState("this is a string data.");
        Parsec<String, Character> parser =
                new Text("this").then(space()).then(new Ahead<>(new Text("is")));

        String re = parser.parse(state);

        assertEquals(re, "is");
        assertThat("Expect status stop after this (5) but is. ", state.status(), IsEqual.equalTo(5));
    }

    @Test
    public void fail() throws Exception{
        State<Character, Integer, Integer> state = newState("this is a string.");
        Parsec<String, Character> parser =
                new Text("this").then(space()).then(new Ahead<>(new Text(" is")));

        try {
            String re = parser.parse(state);
        } catch (Exception e) {
            assertThat("Expect status stop after this (5) but is. ", state.status(), IsEqual.equalTo(5));
            assertThat("Expect the parser fail when try match \" is\"",
                    e, IsInstanceOf.instanceOf(ParsecException.class));
        }
    }
}
