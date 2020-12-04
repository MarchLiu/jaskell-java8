package jaskell.parsec;

import java.util.List;
import java.util.Optional;

/**
 * Created by march on 16/9/12.
 * helper toolbox for combinator.
 */
public class Combinator {
    public static <E, T, Status, Tran> Try<E, T, Status, Tran> attempt(Parsec<E, T, Status, Tran> parser) {
        return new Try<>(parser);
    }

    public static <E, T, Status, Tran> Ahead<E, T, Status, Tran> ahead(Parsec<E, T, Status, Tran> parser) {
        return new Ahead<>(parser);
    }

    @SafeVarargs
    public static <E, T, Status, Tran> Choice<E, T, Status, Tran> choice(Parsec<E, T, Status, Tran> ... parsers) {
        return new Choice<>(parsers);
    }

    public static <E, T, Status, Tran> Choice<E, T, Status, Tran> choice(List<Parsec<E, T, Status, Tran>> parsers) {
        return new Choice<>(parsers);
    }

    public static <E, T, Status, Tran> Many<E, T, Status, Tran> many(Parsec<E, T, Status, Tran> parser) {
        return new Many<>(parser);
    }

    public static <E, T, Status, Tran> Many1<E, T, Status, Tran> many1(Parsec<E, T, Status, Tran> parser) {
        return new Many1<>(parser);
    }

    public static <E, T, L, Status, Tran> ManyTill<E, T, L, Status, Tran> manyTill(
            Parsec<E, T, Status, Tran> parser, Parsec<E, L, Status, Tran> end) {
        return new ManyTill<>(parser, end);
    }

    public static <E, T, Status, Tran> Skip<E, T, Status, Tran> skip(Parsec<E, T, Status, Tran> parser) {
        return new Skip<>(parser);
    }

    public static <E, T, Status, Tran> Skip1<E, T, Status, Tran>
    skip1(Parsec<E, T, Status, Tran> parser) {
        return new Skip1<>(parser);
    }

    public static <E, T, Sep, Status, Tran> SepBy<E, T, Sep, Status, Tran> sepBy(
            Parsec<E, T, Status, Tran> parser, Parsec<E, Sep, Status, Tran> by) {
        return new SepBy<>(parser, by);
    }

    public static <E, T, Sep, Status, Tran> SepBy1<E, T, Sep, Status, Tran> sepBy1(
        Parsec<E, T, Status, Tran> parser, Parsec<E, Sep, Status, Tran> by) {
        return new SepBy1<>(parser, by);
    }

    public static <E, T, Status, Tran> Find<E, T, Status, Tran> find(Parsec<E, T, Status, Tran> parser) {
        return new Find<>(parser);
    }

    public static <E, T, O, C, Status, Tran> Between<E, T, O, C, Status, Tran> between(
        Parsec<E, O, Status, Tran> open, Parsec<E, C, Status, Tran> close,
        Parsec<E, T, Status, Tran> parser){
        return new Between<>(open, close, parser);
    }

    public static <E, T, Status, Tran> Option<E, T, Status, Tran> option(Parsec<E, T, Status, Tran> parser){
        return new Option<>(parser);
    }
}
