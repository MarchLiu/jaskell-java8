package jaskell.sql;

import static jaskell.sql.SQL.l;
import static jaskell.sql.SQL.n;
import static jaskell.sql.SQL.select;
import static jaskell.sql.SQL.with;

import org.junit.Assert;
import org.junit.Test;

public class WithTest {

    @Test
    public void BasicTest0(){
        final String script =
                "WITH t1 AS (SELECT n FROM t), t2 AS (SELECT m FROM t) SELECT m * n FROM t1 JOIN t2 ON t1.n != t2.m";
        Query query = with("t1").as(select("n").from("t"))
                .cte("t2").as(select("m").from("t"))
                .select(l("m * n")).from("t1").join(n("t2")).on(n("t1.n").ne(n("t2.m")));
        Assert.assertEquals(script, query.script());
    }

    @Test
    public void BasicTest1(){
        final String script =
                "WITH RECURSIVE t(f) AS (SELECT 1 UNION SELECT f+1 FROM t WHERE f < 100) SELECT f FROM t";
        Query query = with().recursive().name("t(f)")
                .as(select(l(1)).union().select(l("f+1")).from("t").where(n("f").lt(100)))
                .select("f").from("t");
        Assert.assertEquals(script, query.script());

    }
}
