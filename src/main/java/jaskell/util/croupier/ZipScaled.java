package jaskell.util.croupier;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2023/03/28 18:52
 */
public class ZipScaled<T> implements Poker<T> {
    private final Scale<T> scale;
    private final FairPoker<Integer> fair;
    private final Scaled<Integer[]> scaled;

    public ZipScaled(Scale<T> scale, Random random) {
        this.scale = scale;
        this.fair = new FairPoker<>(random);
        this.scaled = new Scaled<>(value -> value[1], random);
    }

    @Override
    public Optional<Integer> select(List<? extends T> cards) {
        if (cards == null || cards.isEmpty()) {
            return Optional.empty();
        }
        if (cards.size() == 1) {
            return Optional.of(0);
        }

        Map<Integer, List<Integer>> group = new TreeMap<>();
        for (int position = 0; position < cards.size(); position++) {
            T card = cards.get(position);
            int weight = scale.weight(card);
            List<Integer> positions = group.getOrDefault(weight, new ArrayList<>());
            positions.add(position);
            group.put(weight, positions);
        }

        List<Integer[]> weightsTable = group.keySet().stream()
                .map(w -> {
                    Integer[] row = new Integer[2];
                    row[0] = w;
                    row[1] = w * group.get(w).size();
                    return row;
                }).collect(Collectors.toList());
        return scaled.select(weightsTable)
                .map(idx -> weightsTable.get(idx)[0])
                .map(group::get)
                .flatMap(positions -> fair.select(positions).map(positions::get));
    }
}
