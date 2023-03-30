package jaskell.croupier;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2023/03/28 18:53
 */
public class LiteScaled<T> implements Poker<T> {
    private final Scale<T> scale;

    private final Random random;

    public LiteScaled(Scale<T> scale, Random random) {
        this.scale = scale;
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

//        List<Integer> steps = IntStream
//                .range(0, cards.size())
//                .mapToObj(idx -> {
//                    Integer[] pair = new Integer[2];
//                    pair[0] = idx;
//                    pair[1] = scale.weight(cards.get(idx));
//                    return pair;
//                }).flatMap(pair -> IntStream.range(0, pair[1])
//                        .mapToObj(x -> pair[0]))
//                .collect(Collectors.toList());

        List<Integer> steps = new ArrayList<>();
        for (int idx=0; idx < cards.size(); idx ++) {
            int weight = scale.weight(cards.get(idx));
            for(int i=0; i < weight; i++){
                steps.add(idx);
            }
        }

        int score = random.nextInt(steps.size());

        return Optional.of(steps.get(score));
    }
}
