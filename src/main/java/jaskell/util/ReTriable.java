package jaskell.util;

import java.util.function.*;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/12/06 13:56
 */
public class ReTriable<T> implements Supp<T> {
    private final Supp<T> supplier;
    final private int times;

    private int rest = 0;

    private BiConsumer<Throwable, Integer> onErr;

    public ReTriable(Supp<T> supplier) {
        this.supplier = supplier;
        times = 3;
        rest = 3;
        onErr = (err, rest) -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Override
    public T get() throws Throwable {
        Throwable e = null;
        while (rest > 0) {
            try {
                return supplier.get();
            } catch (Exception err){
                e = err;
                try {
                    onErr.accept(err, rest);
                } catch (Exception exception){
                    rest --;
                }
                rest --;
            }
        }
        if(e != null) {
            throw e;
        } else {
            throw new IllegalStateException(String.format("unknown exception after failed %d times", this.times));
        }
    }

    public ReTriable(Supp<T> supplier, int times) {
        this.supplier = supplier;
        this.times = times;
        this.rest = times;
    }

    public ReTriable(Supp<T> supplier, int times, BiConsumer<Throwable, Integer> onErr) {
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

    public static <T> T times(int times, Supplier<T> supplier) throws Throwable {
        Throwable e = null;
        int rest = times;
        while (rest > 0) {
            try {
                return supplier.get();
            } catch (Exception err){
                e = err;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException){
                    rest --;
                }
                rest --;
            }
        }
        if(e != null) {
            throw e;
        } else {
            throw new IllegalStateException(String.format("unknown exception after failed %d times", times));
        }
    }

    public static <T> T retries(int times, Supp<T> supplier, BiConsumer<Throwable, Integer> onErr) {
        Throwable e = null;
        int rest = times;
        while (rest > 0) {
            try {
                return supplier.get();
            } catch (Throwable err){
                e = err;
                onErr.accept(err, rest);
                rest --;
            }
        }
        if(e != null) {
            throw new RuntimeException(e);
        } else {
            throw new IllegalStateException(String.format("unknown exception after failed %d times", times));
        }
    }

    public static <T> ReTriable<T> retry(Supp<T> supplier) {
        return new ReTriable<>(supplier);
    }

    public static <T> ReTriable<T> retry(Supp<T> supplier, int times) {
        return new ReTriable<>(supplier, times);
    }

    public static <T> ReTriable<T> retry(Supp<T> supplier, int times, BiConsumer<Throwable, Integer> onErr) {
        return new ReTriable<>(supplier, times, onErr);
    }


}
