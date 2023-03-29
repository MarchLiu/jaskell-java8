package jaskell.sql;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static jaskell.sql.SQL.count;
import static jaskell.sql.SQL.func;
import static jaskell.sql.SQL.insert;
import static jaskell.sql.SQL.l;
import static jaskell.sql.SQL.n;
import static jaskell.sql.SQL.p;
import static jaskell.sql.SQL.select;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JoinTest {
    private static final String url = "jdbc:sqlite::memory:";
    private static final String table = "test";
    static private Connection conn;
    static private Statement ist = insert().into("test", "pid, content")
            .values(p("pid", int.class),
                    p("content", String.class));

    @BeforeAll
    static public void init() {
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            conn.prepareStatement(
                    "create table test(id integer primary key autoincrement, " +
                            "pid integer references test(id), " +
                            "content text)")
                    .execute();
            Statement ins = ist.cache();
            Query queryLastId = select(func("last_insert_rowid"));
            int last = 1;
            ins.setParameter("content", "one line.");
            for(int i = 0; i < 10; i++){
                ins.setParameter("pid", last);
                ins.execute(conn);
                last = queryLastId.scalar(conn, Integer.class).orElse(0);
                if(last == 0){
                    throw  new IllegalStateException("can't get new id which insert");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterAll
    static public void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initTest(){
        try {
            Query q = select(count()).from("test");
            Optional<Integer> c = q.scalar(conn, Integer.class);
            Assertions.assertTrue(c.isPresent());
            Assertions.assertEquals( 10, c.get().intValue(), "expect 10 lines of data");
        } catch (SQLException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void joinSelfTest(){
        Query q = select("l.id, r.id, l.content, r.content")
                .from(n("test").as("l"))
                    .join(n("test").as("r")).on(l("l.id").eq(l("r.pid")))
                .where(l("l.id").ne(l("r.id")));
        try(PreparedStatement statement = q.prepare(conn);
            ResultSet rs = q.query(statement)){
            while (rs.next()){
                Assertions.assertEquals(rs.getString(3), rs.getString(4));
                Assertions.assertEquals(rs.getInt(1)+1, rs.getInt(2));
            }
        } catch (SQLException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void leftJoinTest0(){
        Query q = select("l.id, r.id, l.content, r.content")
                .from(n("test").as("l")
                        .left().join(n("test").as("r")).on(l("l.id=r.pid")))
                .where(l("r.id").isNull());
        try(PreparedStatement statement = q.prepare(conn);
            ResultSet rs = q.query(statement)){
            while (rs.next()){
                Assertions.assertNull(rs.getObject(2));
            }
        } catch (SQLException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void leftJoinTest1(){
        Query q = select("r.id")
                .from(n("test").as("l")
                        .left().join(n("test").as("r")).on(l("l.id=r.pid")))
                .where(l("r.id").isNull());
        try{
            Assertions.assertFalse(q.scalar(conn).isPresent());
        } catch (SQLException e) {
            Assertions.fail(e.getMessage());
        }
    }
}
