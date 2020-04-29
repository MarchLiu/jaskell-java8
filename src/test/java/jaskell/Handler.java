package jaskell;

public interface Handler<T, U> {
    U handle(T arg);
}
