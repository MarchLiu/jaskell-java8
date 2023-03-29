package jaskell.croupier;

import java.util.List;
import java.util.Optional;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2023/03/28 18:54
 */
public interface Poker<T> {
    Optional<Integer> select(List<? extends T> cards);
}
