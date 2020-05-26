package jaskell.sql;

import static jaskell.sql.Func.max;
import static jaskell.sql.SQL.delete;
import static jaskell.sql.SQL.insert;
import static jaskell.sql.SQL.l;
import static jaskell.sql.SQL.n;
import static jaskell.sql.SQL.p;
import static jaskell.sql.SQL.select;
import static jaskell.sql.SQL.update;

import org.junit.*;
import org.junit.runners.MethodSorters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WriteTest {
    private static final String url = "jdbc:sqlite::memory:";
    private static final String table = "test";

    static private Connection conn;

    @BeforeClass
    static public void connect() {
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            conn.prepareStatement("create table test(id integer primary key autoincrement, content text)")
                .execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterClass
    static public void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertTest() {
        Statement query = insert().into(table, "content").values(p("data")).cache();
        try(PreparedStatement statement = query.prepare(conn)){
            IntStream.range(0, 10).mapToObj(x->String.format("write %dth log", x)).forEach(log->{
                try {
                    query.setParameter("data", log);
                    query.syncParameters(statement);
                    statement.execute();
                    Assert.assertEquals("insert one should get count 1 but {}",
                            statement.getUpdateCount(),
                            1);
                } catch (SQLException e) {
                    Assert.fail(Arrays.toString(e.getStackTrace()));            }
            });
        } catch (SQLException e) {
            Assert.fail(Arrays.toString(e.getStackTrace()));
        }
    }

    @Test
    public void updateTest() throws SQLException {
        AtomicLong id = new AtomicLong();
        Query findIdQuery = select(max(n("id")).as("id")).from(table);
        try{
            Optional<Integer> re = findIdQuery.scalar(conn, Integer.class);
            if(re.isPresent()) {
                id.set(re.get());
            }else{
                System.out.println("data not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement statement = update(table).set("content", p("data"))
                .where(l("id").eq(p("id", Integer.class)));
        statement.setParameter("id", id.get());
        statement.setParameter("data", "rewritten");
        statement.execute(conn);

        Query query = select("content").from(table).where(l("id").eq(id.get()));
        Optional<String> re = query.scalar(conn, String.class);
        if (re.isPresent()) {
            Assert.assertEquals("rewritten", re.get());
        }else{
            Assert.fail("data updated not found");
        }
    }

    @Test
    public void zooCleanTest(){
        Statement statement = delete().from(table);
        try{
            statement.execute(conn);
            Assert.assertTrue(true);
        } catch (SQLException|IllegalStateException e) {
            Assert.fail(e.getMessage());
        }
    }
}
