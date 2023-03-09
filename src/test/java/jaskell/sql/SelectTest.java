package jaskell.sql;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static jaskell.sql.SQL.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SelectTest {

    @Test
    public void basicTest0() {
        Query query = select(func("now"));
        assertEquals("SELECT now()", query.script(), "{} should be {}");
    }

    @Test
    public void queryTest0() {
        Query query = select("id, content").from("log");
        assertEquals("SELECT id, content FROM log",
                query.script(),
                "{} should be {}");
    }

    @Test
    public void queryWhereTest0() {
        Query query = select("id, content").from("log").where(n("id").gt(l(1000)));
        assertEquals("SELECT id, content FROM log WHERE id > 1000",
                query.script(),
                "{} should be {}");

    }

    @Test
    public void queryWhereTest1() {
        Query query = select("id, content")
                .from("log")
                .where(n("id").gt(p("start")));
        assertEquals("SELECT id, content FROM log WHERE id > ?",
                query.script(),
                "{} should be {}");
        assertEquals("start",
                query.parameters().get(0).key().toString(),
                "should get first argument named start");
    }

    @Test
    public void selectPagedTest0() {
        Query query = select("id", "content").from("log").limit(20).offset(60);
        assertEquals("SELECT id, content FROM log LIMIT 20 OFFSET 60",
                query.script(),
                "{} should be {}");
    }

}
