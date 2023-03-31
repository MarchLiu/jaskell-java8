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
class Inverted<T> implements Poker<T> {
    private final Random random;

    public Inverted(Random random) {
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
        double value = random.nextFloat() * range;
        int score = (cards.size() - (int) Math.floor(Math.exp(value)));
        return Optional.of(score);
    }
}
