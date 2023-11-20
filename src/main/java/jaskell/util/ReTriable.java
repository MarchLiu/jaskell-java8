package jaskell.util;


import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/12/06 13:56
 */
public class ReTriable<T> implements Supplier<T>, Triable<T> {
    private final Supplier<T> supplier;
    final private int times;

    private Integer rest = 0;

    private BiFunction<Exception, Integer, Integer> onErr;

    public ReTriable(Supplier<T> supplier) {
        this.supplier = supplier;
        times = 3;
        rest = 3;
        onErr = (err, rest) -> {
            try {
                Thread.sleep(1000);
                return rest;
            } catch (InterruptedException e) {
                return rest - 1;
            }
        };
    }

    @Override
    public T get() throws Exception {
        Exception e = null;
        while (rest > 0) {
            try {
                return supplier.get();
            } catch (Exception err) {
                e = err;
                try {
                    rest = onErr.apply(err, rest);
                } catch (Exception exception) {
                    rest--;
                }
                rest--;
            }
        }
        if (e != null) {
            throw e;
        } else {
            throw new IllegalStateException(String.format("unknown exception after failed %d times", this.times));
        }
    }

    public Try<T> tryIt() {
        return Try.tryIt(this);
    }

    public ReTriable(Supplier<T> supplier, int times) {
        this.supplier = supplier;
        this.times = times;
        this.rest = times;
    }

    public ReTriable(Supplier<T> supplier, int times, BiFunction<Exception, Integer, Integer> onErr) {
        this.supplier = supplier;
        this.times = times;
        this.rest = times;
        this.onErr = onErr;
    }

    public int getRetries() {
        return times;
    }

    public int getRest() {
        return rest;
    }

    public static <T> T times(int times, Supplier<T> supplier) throws Exception {
        Exception e = null;
        int rest = times;
        while (rest > 0) {
            try {
                return supplier.get();
            } catch (Exception err) {
                e = err;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    rest--;
                }
                rest--;
            }
        }
        if (e != null) {
            throw e;
        } else {
            throw new IllegalStateException(String.format("unknown exception after failed %d times", times));
        }
    }

    public static <T> T retries(int times, Supplier<T> supplier, BiFunction<Exception, Integer, Integer> onErr) throws Exception {
        Exception e = null;
        int rest = times;
        while (rest > 0) {
            try {
                return supplier.get();
            } catch (Exception err) {
                e = err;
                rest = onErr.apply(err, rest);
                rest--;
            }
        }
        if (e != null) {
            throw new RuntimeException(e);
        } else {
            throw new IllegalStateException(String.format("unknown exception after failed %d times", times));
        }
    }

    public static <T> ReTriable<T> retry(Supplier<T> supplier) {
        return new ReTriable<>(supplier);
    }

    public static <T> ReTriable<T> retry(Supplier<T> supplier, int times) {
        return new ReTriable<>(supplier, times);
    }

    public static <T> ReTriable<T> retry(Supplier<T> supplier, int times, BiFunction<Exception, Integer, Integer> onErr) {
        return new ReTriable<>(supplier, times, onErr);
    }

}
