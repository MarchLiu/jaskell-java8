package jaskell.parsec;

import java.util.List;
import java.util.Optional;

/**
 * Created by march on 16/9/12.
 * helper toolbox for combinator.
 */
public class Combinator {
    public static <T, E> Parsec<T, E> attempt(Parsec<T, E> parser) {
        return new Try<>(parser);
    }

    public static <T, E> Parsec<T, E> ahead(Parsec<T, E> parser) {
        return new Ahead<>(parser);
    }

    @SafeVarargs
    public static <T, E> Parsec<T, E> choice(Parsec<T, E> ... parsers) {
        return new Choice<>(parsers);
    }

    public static <T, E> Parsec<List<T>, E> many(Parsec<T, E> parser) {
        return new Many<>(parser);
    }

    public static <T, E> Parsec<List<T>, E> many1(Parsec<T, E> parser) {
        return new Many1<>(parser);
    }

    public static <T, L, E> Parsec<List<T>,  E> manyTill(
            Parsec<T, E> parser, Parsec<L, E> end) {
        return new ManyTill<>(parser, end);
    }

    public static <T, E> Parsec<T, E> skip(Parsec<T, E> parser) {
        return new Skip<>(parser);
    }

    public static <T, E> Parsec<T, E> skip1(Parsec<T, E> parser) {
        return new Skip1<>(parser);
    }

    public static <T, S, E> Parsec<List<T>, E> sepBy(
            Parsec<T, E> parser, Parsec<S, E> by) {
        return new SepBy<>(parser, by);
    }

    public static <T, S, E> Parsec<List<T>, E> sepBy1(
            Parsec<T, E> parser, Parsec<S, E> by) {
        return new SepBy1<>(parser, by);
    }

    public static <T, E> Parsec<T, E> find(Parsec<T, E> parser) {
        return new Find<>(parser);
    }

    public static <T, E, O, C> Parsec<T, E> between(Parsec<O, E> open,
                                                                Parsec<C, E> close,
                                                                Parsec<T, E> parser){
        return new Between<>(open, close, parser);
    }

    public static <T, E> Parsec<Optional<T>, E> option(Parsec<T, E> parser){
        return new Option<>(parser);
    }
}
