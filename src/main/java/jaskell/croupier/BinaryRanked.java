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
 * @since 2023/03/28 18:53
 */
public class BinaryRanked<T> implements Poker<T> {
    private final Rank<T> rank;

    private final Random random;

    public BinaryRanked(Rank<T> rank, Random random) {
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
        steps.add(0.0);
        for (T card : cards) {
            Double rnk = rank.rank(card);
            steps.add(rnk + steps.get(steps.size() - 1));
        }

        Double score = random.nextDouble() * steps.get(steps.size() - 1);
        int index = steps.size() / 2;
        int low = 0;
        int high = steps.size() - 1;
        while (true) {
            if (steps.get(index) <= score && steps.get(index + 1) > score) {
                return Optional.of(index);
            }
            if (steps.get(index) > score) {
                high = index;
                index -= Math.max((index - low) / 2, 1);
            } else {
                low = index;
                index += Math.max((high - index) / 2, 1);
            }
        }
    }
}
