package jaskell.util.croupier;

import java.util.*;

/**
 * Random selectors for list
 *
 * @author mars
 * @version 1.0.0
 * @since 2023/03/26 22:20
 */
public class Croupier<T> {
    private final Poker<T> poker;

    public Croupier() {
        poker = new FairPoker<T>(new Random());
    }

    public Croupier(Random random) {
        this.poker = new FairPoker<T>(random);

    }

    public Croupier(long seed) {
        this.poker = new FairPoker<T>(new Random(seed));
    }

    public Croupier(Poker<T> poker) {
        this.poker = poker;
    }

    public List<T> select(List<T> list, int size) {
        List<T> buffer = new ArrayList<>(list);
        List<T> result = new ArrayList<>();
        while (buffer.size() > 0 && result.size() < size) {
            randDraw(buffer).ifPresent(result::add);
        }
        return result;
    }

    /**
     * Rand select one index from list. The list is immutable in this method
     *
     * @param list item's list
     * @return The index been selected. Null will be return if list is empy.
     */
    public Optional<Integer> randIndex(List<? extends T> list) {
        return poker.select(list);
    }

    /**
     * Rand select one element from list. The list is immutable in this method
     *
     * @param list items list
     * @return One item of list. If list is empty, return null
     */
    public Optional<T> randSelect(List<? extends T> list) {
        Optional<Integer> selected = poker.select(list);
        return selected.map(list::get);
    }

    /**
     * Rand select times element from list. The list is immutable in this method
     *
     * @param list items list
     * @param size result max size
     * @return One item of list. If list is empty, return null
     */
    public List<T> randSelect(List<T> list, int size) {
        List<T> buffer = new ArrayList<>(list);
        return randDraw(buffer, size);
    }


    /**
     * Rand select one element from list. The list is mutable in this method. The item will be remove while been choice.
     *
     * @param list items list
     * @return One item of list. If list is empty, return null
     */
    public Optional<T> randDraw(List<T> list) {
        if (list == null || list.size() == 0) {
            return Optional.empty();
        }
        return randIndex(list).map(idx -> list.remove(idx.intValue()));
    }

    public List<T> randDraw(List<T> list, int size) {
        List<T> result = new ArrayList<>();
        while (!list.isEmpty() && result.size() < size) {
            randDraw(list).ifPresent(result::add);
        }
        return result;
    }

    public static <T> Croupier<T> fair() {
        return new Croupier<T>();
    }

    public static <T> Croupier<T> fair(Random random) {
        return new Croupier<T>(random);
    }

    public static <T> Croupier<T> fair(long seed) {
        return new Croupier<T>(seed);
    }

    public static <T> Croupier<T> damping() {
        return new Croupier<T>(new DampingPoker<>(new Random()));
    }

    public static <T> Croupier<T> damping(Random random) {
        return new Croupier<T>(new DampingPoker<>(random));
    }

    public static <T> Croupier<T> damping(long seed) {
        return new Croupier<T>(new DampingPoker<>(new Random(seed)));
    }

    public static <T> Croupier<T> inverted() {
        return new Croupier<T>(new InvertedPoker<>(new Random()));
    }

    public static <T> Croupier<T> inverted(Random random) {
        return new Croupier<T>(new InvertedPoker<>(random));
    }

    public static <T> Croupier<T> inverted(long seed) {
        return new Croupier<T>(new InvertedPoker<>(new Random(seed)));
    }

    public static <T> Croupier<T> byWeight(Scale<T> scale) {
        Random random = new Random();
        Poker<T> porker = new Scaled<>(scale, random);
        return new Croupier<>(porker);
    }

    public static <T> Croupier<T> byWeight(Scale<T> scale, Random random) {
        Poker<T> porker = new Scaled<>(scale, random);
        return new Croupier<>(porker);
    }

    public static <T> Croupier<T> byWeight(Scale<T> scale, long seed) {
        Random random = new Random(seed);
        Poker<T> porker = new Scaled<>(scale, random);
        return new Croupier<>(porker);
    }

    public static <T> Croupier<T> byRank(Rank<T> rank) {
        Random random = new Random();
        Poker<T> porker = new Ranked<>(rank, random);
        return new Croupier<>(porker);
    }

    public static <T> Croupier<T> byRank(Rank<T> rank, Random random) {
        Poker<T> porker = new Ranked<>(rank, random);
        return new Croupier<>(porker);
    }

    public static <T> Croupier<T> byRank(Rank<T> rank, long seed) {
        Random random = new Random(seed);
        Poker<T> porker = new Ranked<>(rank, random);
        return new Croupier<>(porker);
    }

    public static <T> Croupier<T> byZipScaled(Scale<T> scale) {
        Random random = new Random();
        Poker<T> porker = new ZipScaled<>(scale, random);
        return new Croupier<>(porker);
    }

    public static <T> Croupier<T> byZipScaled(Scale<T> scale, Random random) {
        Poker<T> porker = new ZipScaled<>(scale, random);
        return new Croupier<>(porker);
    }

    public static <T> Croupier<T> byZipScaled(Scale<T> scale, long seed) {
        Random random = new Random(seed);
        Poker<T> porker = new ZipScaled<>(scale, random);
        return new Croupier<>(porker);
    }
}
