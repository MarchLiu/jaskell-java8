package jaskell.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/12/06 13:56
 */
public final class Try<T> {
    final Object slot;
    final boolean _ok;
    int idx = 0;

    public Try(T val) {
        slot = val;
        _ok = true;
    }

    public Try(Exception err) {
        slot = err;
        _ok = false;
    }

    public Try<T> or(Try<T> other) {
        if (_ok) {
            return this;
        } else {
            return other;
        }
    }

    @SuppressWarnings("unchecked")
    public <U> Try<U> map(Function<T, U> mapper) {
        if (_ok) {
            try {
                return Try.success(mapper.apply((T) slot));
            } catch (Exception err) {
                return Try.failure(err);
            }
        } else {
            return Try.failure((Exception) slot);
        }
    }

    public Try<T> recover(Function<Exception, T> func) {
        if (_ok) {
            return this;
        } else {
            try {
                return Try.success(func.apply((Exception) slot));
            } catch (Exception err) {
                return Try.failure(err);
            }
        }
    }

    public Try<T> recoverToTry(Function<Exception, Try<T>> func) {
        if (_ok) {
            return this;
        } else {
            try {
                return func.apply((Exception) slot);
            } catch (Exception err) {
                return Try.failure(err);
            }
        }
    }

    public <U, R> Try<R> map2(Try<U> other, BiFunction<? super T, ? super U, R> mapper) {
        if (this.isErr()) {
            return Try.failure(this.error());
        }
        if (other.isErr()) {
            return Try.failure(other.error());
        }
        try {
            return Try.success(mapper.apply(this.get(), other.get()));
        } catch (Exception err) {
            return Try.failure(err);
        }
    }

    public <U, R> Try<R> flatMap2(Try<U> other, BiFunction<? super T, ? super U, Try<R>> mapper) {
        if (this.isErr()) {
            return Try.failure(this.error());
        }
        if (other.isErr()) {
            return Try.failure(other.error());
        }
        try {
            return mapper.apply(this.get(), other.get());
        } catch (Exception err) {
            return Try.failure(err);
        }
    }

    @SuppressWarnings("unchecked")
    public T get() throws Exception {
        if (_ok) {
            return (T) slot;
        } else {
            throw (Exception) slot;
        }
    }

    @SuppressWarnings("unchecked")
    public T orElse(T other) {
        if (_ok) {
            return (T) slot;
        } else {
            return other;
        }
    }

    @SuppressWarnings("unchecked")
    public T orElseGet(Try<? extends T> other) throws Exception {
        if (_ok) {
            return (T) slot;
        } else {
            return other.get();
        }
    }

    @SuppressWarnings("unchecked")
    public T getOr(Function<? super Exception, ? extends T> other) throws Exception {
        if (_ok) {
            return (T) slot;
        } else {
            return other.apply((Exception) slot);
        }
    }

    @SuppressWarnings("unchecked")
    public T getRecovery(Function<? super Exception, Try<? extends T>> other) throws Exception {
        if (_ok) {
            return (T) slot;
        } else {
            return other.apply((Exception) slot).get();
        }
    }

    public boolean isOk() {
        return _ok;
    }

    public boolean isErr() {
        return !_ok;
    }

    @SuppressWarnings("unchecked")
    public <U> Try<U> flatMap(Function<? super T, Try<U>> mapper) {
        if (_ok) {
            try {
                return mapper.apply((T) slot);
            } catch (Exception err) {
                return Try.failure(err);
            }
        } else {
            return Try.failure((Exception) this.slot);
        }
    }

    @SuppressWarnings("unchecked")
    public Try<Void> foreach(Consumer<T> consumer) {
        if (_ok) {
            try {
                consumer.accept((T) slot);
                return success(null);
            } catch (Exception err) {
                return failure(err);
            }
        }
        return success(null);
    }

    @SuppressWarnings("unchecked")
    public boolean anyMatch(Predicate<T> test) {
        if (_ok) {
            return test.test((T) slot);
        } else {
            return false;
        }
    }

    public Exception error() {
        return (Exception) this.slot;
    }

    public static <T> Try<T> success(T value) {
        return new Try<>(value);
    }

    public static <T> Try<T> failure(Exception err) {
        return new Try<>(err);
    }

    public static <T> Try<T> failure(String message) {
        return new Try<>(new Exception(message));
    }

    public static <T> Try<T> tryIt(Supplier<T> supplier) {
        try {
            return Try.success(supplier.get());
        } catch (Exception err) {
            return Try.failure(err);
        }
    }

    public static <T, U> Try<U> call(Function<T, U> func, T arg) throws Exception {
        try {
            return Try.success(func.apply(arg));
        } catch (Exception err) {
            return Try.failure(err);
        }
    }

    /**
     * Collect two Try items and then map the BiFunction
     *
     * @param t1   try item 1
     * @param t2   try item 2
     * @param func the map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <R>  type of result at last
     * @return return a Try include result of the biFunction
     */
    static <T, U, R> Try<R> joinMap(Try<T> t1, Try<U> t2,
                                    BiFunction<? super T, ? super U, ? extends R> func) {
        try {
            T r1 = t1.get();
            U r2 = t2.get();
            return Try.success(func.apply(r1, r2));
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    /**
     * Collect two Try items and then map to a BiFunction return Try
     *
     * @param t1   try item 1
     * @param t2   try item 2
     * @param func the flat map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <R>  type of result
     * @return return the flat map biFunction's result
     */
    static <T, U, R> Try<R> joinFlatMap(Try<T> t1, Try<U> t2,
                                        BiFunction<? super T, ? super U, Try<? extends R>> func) {
        try {
            T r1 = t1.get();
            U r2 = t2.get();
            return func.apply(r1, r2).map(a -> a);
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    /**
     * Collect three Try items and then map to a TriFunction
     *
     * @param t1   try item 1
     * @param t2   try item 2
     * @param t3   try item 3
     * @param func the map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <R>  type of result
     * @return return a Try include result of the triFunction
     */
    static <T, U, V, R> Try<R> joinMap3(Try<T> t1, Try<U> t2, Try<V> t3,
                                        TriFunction<? super T, ? super U, ? super V, ? extends R> func) {
        try {
            T r1 = t1.get();
            U r2 = t2.get();
            V r3 = t3.get();
            return Try.success(func.apply(r1, r2, r3));
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    /**
     * Collect three Try items and then flat map to a TriFunction return Try item
     *
     * @param t1   try item 1
     * @param t2   try item 2
     * @param t3   try item 3
     * @param func the flat map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <R>  type of item included in function's result
     * @return return Try result of the triFunction
     */
    static <T, U, V, R> Try<R> joinFlatMap3(Try<T> t1, Try<U> t2, Try<V> t3,
                                            TriFunction<? super T, ? super U, ? super V, Try<? extends R>> func) {
        try {
            T r1 = t1.get();
            U r2 = t2.get();
            V r3 = t3.get();
            return func.apply(r1, r2, r3).map(a -> a);
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    /**
     * Collect four Try items and then map to a Function4
     *
     * @param t1   try item 1
     * @param t2   try item 2
     * @param t3   try item 3
     * @param t4   try item 4
     * @param func the map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <W>  type of item 4
     * @param <R>  type of result
     * @return return a Try include result of the function
     */
    static <T, U, V, W, R> Try<R> joinMap4(Try<T> t1, Try<U> t2, Try<V> t3, Try<W> t4,
                                           Function4<? super T, ? super U, ? super V, ? super W,
                                                   ? extends R> func) {
        try {
            T r1 = t1.get();
            U r2 = t2.get();
            V r3 = t3.get();
            W r4 = t4.get();
            return Try.success(func.apply(r1, r2, r3, r4));
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    /**
     * Collect four Try items and then flat map to a Function4
     *
     * @param t1   try item 1
     * @param t2   try item 2
     * @param t3   try item 3
     * @param t4   try item 4
     * @param func the flat map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <W>  type of item 4
     * @param <R>  type of the function's result include
     * @return return result of the function
     */
    static <T, U, V, W, R> Try<R> joinFlatMap4(Try<T> t1, Try<U> t2, Try<V> t3, Try<W> t4,
                                               Function4<? super T, ? super U, ? super V,
                                                       ? super W, Try<? extends R>> func) {
        try {
            T r1 = t1.get();
            U r2 = t2.get();
            V r3 = t3.get();
            W r4 = t4.get();
            return func.apply(r1, r2, r3, r4).map(a -> a);
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    /**
     * Collect five Try items and then map to a Function5
     *
     * @param t1   try item 1
     * @param t2   try item 2
     * @param t3   try item 3
     * @param t4   try item 4
     * @param t5   try item 5
     * @param func the map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <W>  type of item 4
     * @param <X>  type of item 5
     * @param <R>  type of result
     * @return return a Try include result of the function
     */
    static <T, U, V, W, X, R> Try<R> joinMap5(Try<T> t1, Try<U> t2, Try<V> t3, Try<W> t4, Try<X> t5,
                                              Function5<? super T, ? super U, ? super V,
                                                      ? super W, ? super X, ? extends R> func) {
        try {
            T r1 = t1.get();
            U r2 = t2.get();
            V r3 = t3.get();
            W r4 = t4.get();
            X r5 = t5.get();
            return Try.success(func.apply(r1, r2, r3, r4, r5));
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    /**
     * Collect five Try items and then flat map to a Function5
     *
     * @param t1   try item 1
     * @param t2   try item 2
     * @param t3   try item 3
     * @param t4   try item 4
     * @param t5   try item 5
     * @param func the flat map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <W>  type of item 4
     * @param <X>  type of item 5
     * @param <R>  type of the function's result include
     * @return return result of the function
     */
    static <T, U, V, W, X, R> Try<R> joinFlatMap5(Try<T> t1, Try<U> t2, Try<V> t3, Try<W> t4, Try<X> t5,
                                                  Function5<? super T, ? super U, ? super V,
                                                          ? super W, ? super X, Try<? extends R>> func) {
        try {
            T r1 = t1.get();
            U r2 = t2.get();
            V r3 = t3.get();
            W r4 = t4.get();
            X r5 = t5.get();
            return func.apply(r1, r2, r3, r4, r5).map(a -> a);
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    /**
     * Collect six Try items and then map to a Function6
     *
     * @param t1   try item 1
     * @param t2   try item 2
     * @param t3   try item 3
     * @param t4   try item 4
     * @param t5   try item 5
     * @param t6   try item 6
     * @param func the map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <W>  type of item 4
     * @param <X>  type of item 5
     * @param <Y>  type of item 6
     * @param <R>  type of result
     * @return return a Try include result of the function
     */
    static <T, U, V, W, X, Y, R> Try<R> joinMap6(Try<T> t1, Try<U> t2, Try<V> t3,
                                                 Try<W> t4, Try<X> t5, Try<Y> t6,
                                                 Function6<? super T, ? super U, ? super V, ? super W,
                                                         ? super X, ? super Y, ? extends R> func) {
        try {
            T r1 = t1.get();
            U r2 = t2.get();
            V r3 = t3.get();
            W r4 = t4.get();
            X r5 = t5.get();
            Y r6 = t6.get();
            return Try.success(func.apply(r1, r2, r3, r4, r5, r6));
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    /**
     * Collect six Try items and then flat map to a Function6
     *
     * @param t1   try item 1
     * @param t2   try item 2
     * @param t3   try item 3
     * @param t4   try item 4
     * @param t5   try item 5
     * @param t6   try item 6
     * @param func the flat map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <W>  type of item 4
     * @param <X>  type of item 5
     * @param <Y>  type of item 6
     * @param <R>  type of the function's result include
     * @return return result of the function
     */
    static <T, U, V, W, X, Y, R> Try<R> joinFlatMap6(Try<T> t1, Try<U> t2, Try<V> t3,
                                                     Try<W> t4, Try<X> t5, Try<Y> t6,
                                                     Function6<? super T, ? super U, ? super V,
                                                             ? super W, ? super X, ? super Y,
                                                             Try<? extends R>> func) {
        try {
            T r1 = t1.get();
            U r2 = t2.get();
            V r3 = t3.get();
            W r4 = t4.get();
            X r5 = t5.get();
            Y r6 = t6.get();
            return func.apply(r1, r2, r3, r4, r5, r6).map(a -> a);
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    /**
     * Collect seven Try items and then map to a Function7
     *
     * @param t1   try item 1
     * @param t2   try item 2
     * @param t3   try item 3
     * @param t4   try item 4
     * @param t5   try item 5
     * @param t6   try item 6
     * @param t7   try item 6
     * @param func the map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <W>  type of item 4
     * @param <X>  type of item 5
     * @param <Y>  type of item 6
     * @param <Z>  type of item 7
     * @param <R>  type of result
     * @return return a Try include result of the function
     */
    static <T, U, V, W, X, Y, Z, R> Try<R> joinMap7(Try<T> t1, Try<U> t2, Try<V> t3, Try<W> t4,
                                                    Try<X> t5, Try<Y> t6, Try<Z> t7,
                                                    Function7<? super T, ? super U, ? super V,
                                                            ? super W, ? super X, ? super Y,
                                                            ? super Z, ? extends R> func) {
        try {
            T r1 = t1.get();
            U r2 = t2.get();
            V r3 = t3.get();
            W r4 = t4.get();
            X r5 = t5.get();
            Y r6 = t6.get();
            Z r7 = t7.get();
            return Try.success(func.apply(r1, r2, r3, r4, r5, r6, r7));
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    /**
     * Collect seven Try items and then flat map to a Function7
     *
     * @param t1   try item 1
     * @param t2   try item 2
     * @param t3   try item 3
     * @param t4   try item 4
     * @param t5   try item 5
     * @param t6   try item 6
     * @param t7   try item 7
     * @param func the flat map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <W>  type of item 4
     * @param <X>  type of item 5
     * @param <Y>  type of item 6
     * @param <Z>  type of item 7
     * @param <R>  type of the function's result include
     * @return return result of the function
     */
    static <T, U, V, W, X, Y, Z, R> Try<R> joinFlatMap7(Try<T> t1, Try<U> t2, Try<V> t3, Try<W> t4,
                                                        Try<X> t5, Try<Y> t6, Try<Z> t7,
                                                        Function7<? super T, ? super U, ? super V,
                                                                ? super W, ? super X, ? super Y,
                                                                ? super Z, Try<? extends R>> func) {
        try {
            T r1 = t1.get();
            U r2 = t2.get();
            V r3 = t3.get();
            W r4 = t4.get();
            X r5 = t5.get();
            Y r6 = t6.get();
            Z r7 = t7.get();
            return func.apply(r1, r2, r3, r4, r5, r6, r7).map(a -> a);
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    /**
     * Collect eight Try items and then map to a Function8
     *
     * @param t1   try item 1
     * @param t2   try item 2
     * @param t3   try item 3
     * @param t4   try item 4
     * @param t5   try item 5
     * @param t6   try item 6
     * @param t7   try item 7
     * @param t8   try item 8
     * @param func the map function
     * @param <S>  type of item 1
     * @param <T>  type of item 2
     * @param <U>  type of item 3
     * @param <V>  type of item 4
     * @param <W>  type of item 5
     * @param <X>  type of item 6
     * @param <Y>  type of item 7
     * @param <Z>  type of item 8
     * @param <R>  type of result
     * @return return a Try include result of the function
     */
    static <S, T, U, V, W, X, Y, Z, R> Try<R> joinMap8(Try<S> t1, Try<T> t2, Try<U> t3, Try<V> t4,
                                                       Try<W> t5, Try<X> t6, Try<Y> t7, Try<Z> t8,
                                                       Function8<? super S, ? super T, ? super U,
                                                               ? super V, ? super W, ? super X,
                                                               ? super Y, ? super Z,
                                                               ? extends R> func) {
        try {
            S r1 = t1.get();
            T r2 = t2.get();
            U r3 = t3.get();
            V r4 = t4.get();
            W r5 = t5.get();
            X r6 = t6.get();
            Y r7 = t7.get();
            Z r8 = t8.get();
            return Try.success(func.apply(r1, r2, r3, r4, r5, r6, r7, r8));
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    /**
     * Collect eight Try items and then flat map to a Function8
     *
     * @param t1   try item 1
     * @param t2   try item 2
     * @param t3   try item 3
     * @param t4   try item 4
     * @param t5   try item 5
     * @param t6   try item 6
     * @param t7   try item 7
     * @param t8   try item 7
     * @param func the flat map function
     * @param <S>  type of item 1
     * @param <T>  type of item 2
     * @param <U>  type of item 3
     * @param <V>  type of item 4
     * @param <W>  type of item 5
     * @param <X>  type of item 6
     * @param <Y>  type of item 7
     * @param <Z>  type of item 8
     * @param <R>  type of the function's result include
     * @return return result of the function
     */
    static <S, T, U, V, W, X, Y, Z, R> Try<R> joinFlatMap8(Try<S> t1, Try<T> t2, Try<U> t3, Try<V> t4,
                                                           Try<W> t5, Try<X> t6, Try<Y> t7, Try<Z> t8,
                                                           Function8<? super S, ? super T, ? super U,
                                                                   ? super V, ? super W, ? super X,
                                                                   ? super Y, ? super Z,
                                                                   Try<? extends R>> func) {
        try {
            S r1 = t1.get();
            T r2 = t2.get();
            U r3 = t3.get();
            V r4 = t4.get();
            W r5 = t5.get();
            X r6 = t6.get();
            Y r7 = t7.get();
            Z r8 = t8.get();
            return func.apply(r1, r2, r3, r4, r5, r6, r7, r8).map(a -> a);
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    static <T> Try<List<T>> all(List<Try<T>> elements) {
        List<T> result = new ArrayList<>();
        for (Try<T> e : elements) {
            if (e.isErr()) {
                return Try.failure(e.error());
            } else {
                try {
                    result.add(e.get());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return Try.success(result);
    }

    static <T> Try<T> any(List<Try<T>> elements) {
        for (Try<T> e : elements) {
            if (e.isOk()) {
                try {
                    return Try.success(e.get());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        Optional<Try<T>> item = elements.stream().filter(Try::isErr).findFirst();
        return item.orElseGet(() -> Try.failure("empty list not include any success item"));
    }

    static <T> Try<List<T>> tryAll(List<Triable<T>> elements) {
        return all(elements.stream().map(Triable::collect)
                .collect(Collectors.toList()));
    }

    static <T> Try<T> tryAny(List<Triable<T>> elements) {
        Try<T> err = null;
        for (Triable<T> e : elements) {
            Try<T> re = e.collect();
            if (re.isOk()) {
                return re;
            } else {
                err = re;
            }
        }
        if (err == null) {
            return Try.failure("empty list not include any success item");
        } else {
            return err;
        }
    }

    @SuppressWarnings("unchecked")
    static <T> Try<List<T>> asyncAll(List<Triable<T>> elements, Executor executor) {
        List<CompletableFuture<Try<T>>> tasks = elements.stream()
                .map(t -> CompletableFuture.supplyAsync(t::collect, executor))
                .collect(Collectors.toList());
        CompletableFuture<Try<T>>[] tsa = new CompletableFuture[tasks.size()];
        tsa = tasks.toArray(tsa);
        CompletableFuture.allOf(tsa);
        List<Try<T>> results = tasks.stream().map((java.util.function.Function<CompletableFuture<Try<T>>, Try<T>>) arg -> {
            try {
                return arg.get();
            } catch (Exception e) {
                return Try.failure(e);
            }
        }).collect(Collectors.toList());
        return Try.all(results);
    }

    @SuppressWarnings("unchecked")
    static <T> Try<List<T>> asyncAll(List<Triable<T>> elements) {
        List<CompletableFuture<Try<T>>> tasks = elements.stream()
                .map(t -> CompletableFuture.supplyAsync(t::collect))
                .collect(Collectors.toList());
        CompletableFuture<Try<T>>[] tsa = new CompletableFuture[tasks.size()];
        tsa = tasks.toArray(tsa);
        CompletableFuture.allOf(tsa);
        List<Try<T>> results = tasks.stream().map((java.util.function.Function<CompletableFuture<Try<T>>, Try<T>>) arg -> {
            try {
                return arg.get();
            } catch (Exception e) {
                return Try.failure(e);
            }
        }).collect(Collectors.toList());
        return Try.all(results);
    }

    @SuppressWarnings("unchecked")
    static <T> Try<T> asyncAny(List<Triable<T>> elements, Executor executor) {
        List<CompletableFuture<Try<T>>> tasks = elements.stream()
                .map(t -> CompletableFuture.supplyAsync(t::collect, executor))
                .collect(Collectors.toList());
        CompletableFuture<Try<T>>[] tsa = new CompletableFuture[tasks.size()];

        try {
            T re = (T) CompletableFuture.anyOf(tsa).get();
            return Try.success(re);
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    @SuppressWarnings("unchecked")
    static <T> Try<T> asyncAny(List<Triable<T>> elements) {
        List<CompletableFuture<Try<T>>> tasks = elements.stream()
                .map(t -> CompletableFuture.supplyAsync(t::collect))
                .collect(Collectors.toList());
        CompletableFuture<Try<T>>[] tsa = new CompletableFuture[tasks.size()];
        tsa = tasks.toArray(tsa);
        try {
            T re = (T) CompletableFuture.anyOf(tsa).get();
            return Try.success(re);
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    static <T> List<T> ifSuccess(List<Try<T>> elements) {
        List<T> result = new ArrayList<>();
        for (Try<T> element : elements) {
            element.foreach(result::add);
        }
        return result;
    }

    static <T> Try<Void> foreach(List<Try<T>> elements, Consumer<T> consumer) {
        try {
            for (Try<T> element : elements) {
                element.foreach(consumer);
            }
            return Try.success(null);
        } catch (Exception err) {
            return Try.failure(err);
        }
    }

}
