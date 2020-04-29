package jaskell.sql;

import static jaskell.sql.SQL.func;
import static jaskell.sql.SQL.l;
import static jaskell.sql.SQL.n;
import static jaskell.sql.SQL.p;
import static jaskell.sql.SQL.select;

import org.junit.Assert;
import org.junit.Test;


public class SelectTest {

    @Test
    public void basicTest0(){
        Query query = select(func("now"));
        Assert.assertEquals("{} should be {}", "select now()", query.script());
    }

    @Test
    public void queryTest0(){
        Query query = select("id, content").from("log");
        Assert.assertEquals("{} should be {}",
                "select id, content from log",
                query.script());
    }

    @Test
    public void queryWhereTest0(){
        Query query = select("id, content").from("log").where(n("id").gt(l(1000)));
        Assert.assertEquals("{} should be {}",
                "select id, content from log where id > 1000",
                query.script());

    }

    @Test
    public void queryWhereTest1(){
        Query query = select("id, content")
                .from("log")
                .where(n("id").gt(p("start")));
        Assert.assertEquals("{} should be {}",
                "select id, content from log where id > ?",
                query.script());
        Assert.assertEquals("should get first argument named start",
                "start",
                query.parameters().get(0).key());
    }

    @Test
    public void selectPagedTest0(){
        Query query = select("id", "content").from("log").limit(20).offset(60);
        Assert.assertEquals("{} should be {}",
                "select id, content from log limit 20 offset 60",
                query.script());
    }

}
