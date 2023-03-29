package jaskell.croupier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2023/03/28 18:52
 */
public class Ranked<T> implements Poker<T> {
    private final Rank<T> rank;

    private final Random random;

    public Ranked(Rank<T> rank, Random random) {
        this.rank = rank;
        this.random = random;
    }

    @Override
    public Optional<Integer> select(List<? extends T> cards) {
        if (cards == null || cards.isEmpty()) {
            return Optional.empty();
        }
        if (cards.size() == 1) {
            return Optional.of(0);
        }

        List<Double> steps = new ArrayList<>();
        steps.add(0d);
        for (T card : cards) {
            double weight = rank.rank(card);
            steps.add(weight + steps.get(steps.size() - 1));
        }

        double score = random.nextDouble() * steps.get(steps.size() - 1);
        for (int idx = 0; idx < cards.size(); idx++) {
            if (steps.get(idx) <= score && steps.get(idx + 1) > score) {
                return Optional.of(idx);
            }
        }
        return Optional.of(cards.size() - 1);
    }
}
