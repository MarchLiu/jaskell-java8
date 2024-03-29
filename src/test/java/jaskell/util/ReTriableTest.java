package jaskell.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2023/11/16 17:41
 */
public class ReTriableTest {
    @Test
    public void testSuccess() throws Exception {
        ReTriable<Integer> zero = ReTriable.retry(() -> 0);
        assertEquals(0, zero.get());
        assertEquals(3, zero.getRest());

    }

    @Test
    public void testRetries2() throws Exception {
        Supplier<Integer> supplier = new Supplier<Integer>() {
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
    public void testFailed() throws Exception {
        Supplier<Integer> supplier = new Supplier<Integer>() {

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
