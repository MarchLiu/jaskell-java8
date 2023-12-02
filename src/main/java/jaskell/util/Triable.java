package jaskell.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2023/11/20 16:30
 */
public interface Triable<T> {
    Try<T> collect();

    default Future<Try<T>> async() {
        return CompletableFuture.supplyAsync(this::collect);
    }

    default Future<Try<T>> async(Executor executor) {
        return CompletableFuture.supplyAsync(this::collect, executor);
    }

    default <U> Triable<U> map(Function<T, U> functor) {
        return () -> collect().map(functor);
    }

    default <U> Triable<U> flatMap(Function<T, Try<U>> functor) {
        return () -> collect().flatMap(functor);
    }

    default <U, R> Triable<R> map2(Triable<U> other, BiFunction<? super T, ? super U, R> mapper) {
        return () -> collect().map2(other.collect(), mapper);
    }

    default <U, R> Triable<R> flatMap2(Triable<U> other, BiFunction<? super T, ? super U, Try<R>> mapper) {
        return () -> collect().flatMap2(other.collect(), mapper);
    }

    static <T> Triable<T> success(T value) {
        return () -> Try.success(value);
    }

    static <T> Triable<T> failure(Exception err) {
        return () -> Try.failure(err);
    }

    static <T> Triable<T> failure(String message) {
        return () -> Try.failure(message);
    }

    /**
     * Collect two triable items and then map the BiFunction
     *
     * @param t1   triable item 1
     * @param t2   triable item 2
     * @param func the map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <R>  type of result at last
     * @return return a Try include result of the biFunction
     */
    static <T, U, R> Triable<R> joinMap(Triable<T> t1, Triable<U> t2,
                                        BiFunction<? super T, ? super U, R> func) {
        return () -> t1.collect().map2(t2.collect(), func);
    }

    /**
     * Collect two triable items and then map to a BiFunction return Try
     *
     * @param t1   triable item 1
     * @param t2   triable item 2
     * @param func the flat map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <R>  type of result
     * @return return the flat map biFunction's result
     */
    static <T, U, R> Triable<R> joinFlatMap(Triable<T> t1, Triable<U> t2,
                                            BiFunction<? super T, ? super U, Try<? extends R>> func) {
        return () -> Try.joinFlatMap(t1.collect(), t2.collect(), func);
    }

    /**
     * Collect three triable items and then map to a TriFunction
     *
     * @param t1   triable item 1
     * @param t2   triable item 2
     * @param t3   triable item 3
     * @param func the map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <R>  type of result
     * @return return a Try include result of the triFunction
     */
    static <T, U, V, R> Triable<R> joinMap3(Triable<T> t1, Triable<U> t2, Triable<V> t3,
                                            TriFunction<? super T, ? super U, ? super V, ? extends R> func) {
        return () -> Try.joinMap3(t1.collect(), t2.collect(), t3.collect(), func);
    }

    /**
     * Collect three triable items and then flat map to a TriFunction return triable item
     *
     * @param t1   triable item 1
     * @param t2   triable item 2
     * @param t3   triable item 3
     * @param func the flat map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <R>  type of item included in function's result
     * @return return Try result of the triFunction
     */
    static <T, U, V, R> Triable<R> joinFlatMap3(Triable<T> t1, Triable<U> t2, Triable<V> t3,
                                                TriFunction<? super T, ? super U, ? super V, Try<? extends R>> func) {
        return () -> Try.joinFlatMap3(t1.collect(), t2.collect(), t3.collect(), func);
    }

    /**
     * Collect four triable items and then map to a Function4
     *
     * @param t1   triable item 1
     * @param t2   triable item 2
     * @param t3   triable item 3
     * @param t4   triable item 4
     * @param func the map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <W>  type of item 4
     * @param <R>  type of result
     * @return return a Try include result of the function
     */
    static <T, U, V, W, R> Triable<R> joinMap4(Triable<T> t1, Triable<U> t2, Triable<V> t3, Triable<W> t4,
                                               Function4<? super T, ? super U, ? super V, ? super W, ? extends R> func) {
        return () -> Try.joinMap4(t1.collect(), t2.collect(), t3.collect(), t4.collect(), func);
    }

    /**
     * Collect four triable items and then flat map to a Function4
     *
     * @param t1   triable item 1
     * @param t2   triable item 2
     * @param t3   triable item 3
     * @param t4   triable item 4
     * @param func the flat map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <W>  type of item 4
     * @param <R>  type of the function's result include
     * @return return result of the function
     */
    static <T, U, V, W, R> Triable<R> joinFlatMap4(Triable<T> t1, Triable<U> t2, Triable<V> t3, Triable<W> t4,
                                                   Function4<? super T, ? super U, ? super V, ? super W,
                                                           Try<? extends R>> func) {
        return () -> Try.joinFlatMap4(t1.collect(), t2.collect(), t3.collect(), t4.collect(), func);
    }

    /**
     * Collect five triable items and then map to a Function5
     *
     * @param t1   triable item 1
     * @param t2   triable item 2
     * @param t3   triable item 3
     * @param t4   triable item 4
     * @param t5   triable item 5
     * @param func the map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <W>  type of item 4
     * @param <X>  type of item 5
     * @param <R>  type of result
     * @return return a Try include result of the function
     */
    static <T, U, V, W, X, R> Triable<R> joinMap5(Triable<T> t1, Triable<U> t2, Triable<V> t3, Triable<W> t4,
                                                  Triable<X> t5,
                                                  Function5<? super T, ? super U, ? super V,
                                                          ? super W, ? super X,
                                                          ? extends R> func) {
        return () -> Try.joinMap5(t1.collect(), t2.collect(), t3.collect(), t4.collect(),
                t5.collect(), func);
    }

    /**
     * Collect five triable items and then flat map to a Function5
     *
     * @param t1   triable item 1
     * @param t2   triable item 2
     * @param t3   triable item 3
     * @param t4   triable item 4
     * @param t5   triable item 5
     * @param func the flat map function
     * @param <T>  type of item 1
     * @param <U>  type of item 2
     * @param <V>  type of item 3
     * @param <W>  type of item 4
     * @param <X>  type of item 5
     * @param <R>  type of the function's result include
     * @return return result of the function
     */
    static <T, U, V, W, X, R> Triable<R> joinFlatMap5(Triable<T> t1, Triable<U> t2, Triable<V> t3,
                                                      Triable<W> t4, Triable<X> t5,
                                                      Function5<? super T, ? super U, ? super V, ? super W, ? super X,
                                                              Try<? extends R>> func) {
        return () -> Try.joinFlatMap5(t1.collect(), t2.collect(),
                t3.collect(), t4.collect(), t5.collect(), func);
    }

    /**
     * Collect six triable items and then map to a Function6
     *
     * @param t1   triable item 1
     * @param t2   triable item 2
     * @param t3   triable item 3
     * @param t4   triable item 4
     * @param t5   triable item 5
     * @param t6   triable item 6
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
    static <T, U, V, W, X, Y, R> Triable<R> joinMap6(Triable<T> t1, Triable<U> t2, Triable<V> t3,
                                                     Triable<W> t4, Triable<X> t5, Triable<Y> t6,
                                                     Function6<? super T, ? super U, ? super V, ? super W,
                                                             ? super X, ? super Y, ? extends R> func) {
        return () -> Try.joinMap6(t1.collect(), t2.collect(), t3.collect(),
                t4.collect(), t5.collect(), t6.collect(), func);
    }

    /**
     * Collect six triable items and then flat map to a Function6
     *
     * @param t1   triable item 1
     * @param t2   triable item 2
     * @param t3   triable item 3
     * @param t4   triable item 4
     * @param t5   triable item 5
     * @param t6   triable item 6
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
    static <T, U, V, W, X, Y, R> Triable<R> joinFlatMap6(Triable<T> t1, Triable<U> t2, Triable<V> t3,
                                                         Triable<W> t4, Triable<X> t5, Triable<Y> t6,
                                                         Function6<? super T, ? super U, ? super V,
                                                                 ? super W, ? super X, ? super Y,
                                                                 Try<? extends R>> func) {
        return () -> Try.joinFlatMap6(t1.collect(), t2.collect(), t3.collect(),
                t4.collect(), t5.collect(), t6.collect(), func);
    }

    /**
     * Collect seven triable items and then map to a Function7
     *
     * @param t1   triable item 1
     * @param t2   triable item 2
     * @param t3   triable item 3
     * @param t4   triable item 4
     * @param t5   triable item 5
     * @param t6   triable item 6
     * @param t7   triable item 6
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
    static <T, U, V, W, X, Y, Z, R> Triable<R> joinMap7(Triable<T> t1, Triable<U> t2, Triable<V> t3, Triable<W> t4,
                                                        Triable<X> t5, Triable<Y> t6, Triable<Z> t7,
                                                        Function7<? super T, ? super U, ? super V, ? super W,
                                                                ? super X, ? super Y, ? super Z, ? extends R> func) {
        return () -> Try.joinMap7(t1.collect(), t2.collect(), t3.collect(), t4.collect(),
                t5.collect(), t6.collect(), t7.collect(), func);
    }

    /**
     * Collect seven triable items and then flat map to a Function7
     *
     * @param t1   triable item 1
     * @param t2   triable item 2
     * @param t3   triable item 3
     * @param t4   triable item 4
     * @param t5   triable item 5
     * @param t6   triable item 6
     * @param t7   triable item 7
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
    static <T, U, V, W, X, Y, Z, R> Triable<R> joinFlatMap7(Triable<T> t1, Triable<U> t2, Triable<V> t3, Triable<W> t4,
                                                            Triable<X> t5, Triable<Y> t6, Triable<Z> t7,
                                                            Function7<? super T, ? super U, ? super V,
                                                                    ? super W, ? super X, ? super Y,
                                                                    ? super Z, Try<? extends R>> func) {
        return () -> Try.joinFlatMap7(t1.collect(), t2.collect(), t3.collect(), t4.collect(),
                t5.collect(), t6.collect(), t7.collect(), func);
    }

    /**
     * Collect eight triable items and then map to a Function8
     *
     * @param t1   triable item 1
     * @param t2   triable item 2
     * @param t3   triable item 3
     * @param t4   triable item 4
     * @param t5   triable item 5
     * @param t6   triable item 6
     * @param t7   triable item 7
     * @param t8   triable item 8
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
    static <S, T, U, V, W, X, Y, Z, R> Triable<R> joinMap8(Triable<S> t1, Triable<T> t2, Triable<U> t3, Triable<V> t4,
                                                           Triable<W> t5, Triable<X> t6, Triable<Y> t7, Triable<Z> t8,
                                                           Function8<? super S, ? super T, ? super U,
                                                                   ? super V, ? super W, ? super X,
                                                                   ? super Y, ? super Z, ? extends R> func) {
        return () -> Try.joinMap8(t1.collect(), t2.collect(), t3.collect(), t4.collect(),
                t5.collect(), t6.collect(), t7.collect(), t8.collect(), func);
    }

    /**
     * Collect eight triable items and then flat map to a Function8
     *
     * @param t1   triable item 1
     * @param t2   triable item 2
     * @param t3   triable item 3
     * @param t4   triable item 4
     * @param t5   triable item 5
     * @param t6   triable item 6
     * @param t7   triable item 7
     * @param t8   triable item 7
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
    static <S, T, U, V, W, X, Y, Z, R> Triable<R> joinFlatMap8(Triable<S> t1, Triable<T> t2, Triable<U> t3,
                                                               Triable<V> t4, Triable<W> t5, Triable<X> t6,
                                                               Triable<Y> t7, Triable<Z> t8,
                                                               Function8<? super S, ? super T, ? super U,
                                                                       ? super V, ? super W, ? super X,
                                                                       ? super Y, ? super Z,
                                                                       Try<? extends R>> func) {
        return () -> Try.joinFlatMap8(t1.collect(), t2.collect(), t3.collect(), t4.collect(),
                t5.collect(), t6.collect(), t7.collect(), t8.collect(), func);
    }

    static <T> Triable<List<T>> all(List<Triable<T>> tasks) {
        return () -> {
            List<Try<T>> elements = tasks.stream().map(Triable::collect)
                    .collect(Collectors.toList());
            return Try.all(elements);
        };
    }

    static <T> Triable<T> any(List<Triable<T>> tasks) {
        return () -> {
            Optional<Try<T>> error = Optional.empty();
            for (Triable<T> t : tasks) {
                Try<T> element = t.collect();
                if (element.isOk()) {
                    return element;
                } else {
                    if (!error.isPresent()) {
                        error = Optional.of(element);
                    }
                }
            }
            return error.orElseGet(() -> Try.failure("empty list not include any success item"));
        };
    }

    static <T> Triable<List<T>> tryAll(List<Triable<T>> elements) {
        return () -> Try.all(elements.stream().map(Triable::collect).collect(Collectors.toList()));
    }

    static <T> Triable<T> tryAny(List<Triable<T>> elements) {
        return () -> {
            Optional<Try<T>> err = Optional.empty();
            for (Triable<T> e : elements) {
                Try<T> re = e.collect();
                if (re.isOk()) {
                    return re;
                } else {
                    err = Optional.of(re);
                }
            }
            return err.orElse(Try.failure("empty list not include any success item"));
        };
    }

    @SuppressWarnings("unchecked")
    static <T> Future<Try<List<T>>> asyncAll(List<Triable<T>> elements, Executor executor) {
        List<CompletableFuture<Try<T>>> tasks = elements.stream()
                .map(t -> CompletableFuture.supplyAsync(t::collect, executor))
                .collect(Collectors.toList());
        CompletableFuture<Try<T>>[] tsa = new CompletableFuture[tasks.size()];
        tsa = tasks.toArray(tsa);
        CompletableFuture.allOf(tsa);
        return CompletableFuture.supplyAsync(() -> {
            List<Try<T>> results = tasks.stream().map((java.util.function.Function<CompletableFuture<Try<T>>, Try<T>>) arg -> {
                try {
                    return arg.get();
                } catch (Exception e) {
                    return Try.failure(e);
                }
            }).collect(Collectors.toList());
            return Try.all(results);
        }, executor);
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
