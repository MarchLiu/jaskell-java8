package jaskell.croupier;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2023/03/28 18:53
 */
public class FairPoker<T> implements Poker<T> {
    private final Random random;

    public FairPoker(Random random) {
        this.random = random;
    }

    @Override
    public Optional<Integer> select(List<? extends T> cards) {
        if (cards == null || cards.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(random.nextInt(cards.size()));
    }
}
