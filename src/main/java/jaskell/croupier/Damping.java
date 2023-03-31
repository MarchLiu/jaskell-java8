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
public class Damping<T> implements Poker<T> {
    private final Random random;

    public Damping(Random random) {
        this.random = random;
    }

    @Override
    public Optional<Integer> select(List<? extends T> cards) {
        if (cards == null || cards.size() == 0) {
            return Optional.empty();
        }
        if (cards.size() == 1) {
            return Optional.of(0);
        }
        double range = Math.log(cards.size() + 1);
        double value = random.nextDouble() * range;
        return Optional.of((int) Math.floor(Math.exp(value)) - 1);
    }
}
