package jaskell.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2023/11/16 17:41
 */
public class ReTriableTest {
    @Test
    public void testSuccess() throws Throwable {
        ReTriable<Integer> zero = ReTriable.retry(() -> 0);
        assertEquals(0, zero.get());
        assertEquals(3, zero.getRest());

    }

    @Test
    public void testRetries2() throws Throwable {
        Supp<Integer> supplier = new Supp<Integer>() {
            int rest = 2;

            @Override
            public Integer get() throws Exception {
                if (rest > 0) {
                    rest--;
                    throw new Exception("failed and continue");
                } else {
                    return 10;
                }
            }
        };

        ReTriable<Integer> box = ReTriable.retry(supplier);
        assertEquals(10, box.get());
        assertEquals(1, box.getRest());
    }

    @Test
    public void testFailed() throws Throwable {
        Supp<Integer> supplier = new Supp<Integer>() {
            int rest = 2;

            @Override
            public Integer get() throws Exception {
                throw new Exception("failed and continue");
            }
        };

        ReTriable<Integer> box = ReTriable.retry(supplier);

        assertThrowsExactly(Exception.class, box::get);
        assertEquals(0, box.getRest());
    }
}
