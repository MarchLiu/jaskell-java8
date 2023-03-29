package jaskell.croupier;

import jaskell.croupier.Croupier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2023/03/28 00:50
 */
public class CroupierTest {
    private static List<Integer> data;

    @BeforeAll
    public static void setup() {
        data = IntStream.range(0, 100)
                .boxed()
                .collect(Collectors.toList());
    }

    @Test
    public void testFair() {
        Croupier<Integer> croupier = Croupier.fair();
        Map<Integer, Integer> counter = new TreeMap<>();
        for (int idx = 0; idx < 100000; idx++) {
            Optional<Integer> item = croupier.randSelect(data);
            Assertions.assertTrue(item.isPresent());
            item.ifPresent(num -> counter.put(num, counter.getOrDefault(num, 0) + 1));
        }
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            System.out.printf("fair croupier select %d times %d%n", entry.getKey(), entry.getValue());
        }
    }

    @Test
    public void testDamping() {
        Croupier<Integer> croupier = Croupier.damping();
        Map<Integer, Integer> counter = new TreeMap<>();
        for (int idx = 0; idx < 100000; idx++) {
            Optional<Integer> item = croupier.randSelect(data);
            Assertions.assertTrue(item.isPresent());
            item.ifPresent(num -> counter.put(num, counter.getOrDefault(num, 0) + 1));
        }
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            System.out.printf("damping croupier select %d times %d%n", entry.getKey(), entry.getValue());
        }
    }

    @Test
    public void testInvert() {
        Croupier<Integer> croupier = Croupier.inverted();
        Map<Integer, Integer> counter = new TreeMap<>();
        for (int idx = 0; idx < 100000; idx++) {
            Optional<Integer> item = croupier.randSelect(data);
            Assertions.assertTrue(item.isPresent());
            item.ifPresent(num -> counter.put(num, counter.getOrDefault(num, 0) + 1));
        }
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            System.out.printf("invert croupier select %d times %d%n", entry.getKey(), entry.getValue());
        }
    }

    @Test
    public void testDraw() {
        Croupier<Integer> croupier = Croupier.damping();
        Map<Integer, Integer> counter = new TreeMap<>();
        for (int times = 0; times < 100000; times++) {
            List<Integer> buffer = new ArrayList<>(data);
            List<Integer> items = croupier.randDraw(buffer, 10);
            for (Integer item : items) {
                counter.put(item, counter.getOrDefault(item, 0) + 1);
            }
            Assertions.assertEquals(10, items.size());
            Assertions.assertEquals(data.size() - 10, buffer.size());
        }

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            System.out.printf("damping croupier draw %d times %d%n", entry.getKey(), entry.getValue());
        }
    }

    @Test
    public void testWeight() {
        Random random = new Random();
        Croupier<Integer[]> croupier = Croupier.byWeight(item -> item[1]);
        List<Integer[]> data = IntStream.range(0, 100)
                .mapToObj(idx -> {
                    int value = random.nextInt(10);
                    Integer[] result = new Integer[2];
                    result[0] = idx;
                    result[1] = value;
                    return result;
                }).collect(Collectors.toList());

        Map<Integer, Integer[]> counter = new TreeMap<>();
        for(Integer[] item: data){
            Integer[] values = new Integer[2];
            values[0] = item[1];
            values[1] = 0;
            counter.put(item[0], values);
        }


        for (int times = 0; times < 10000; times++) {
            List<Integer[]> sample = croupier.select(data, 10);
            Assertions.assertEquals(10, sample.size());
            for (Integer[] element : sample) {
                int index = element[0];
                Integer[] item = counter.get(index);
                item[1] = item[1] + 1;
                counter.put(index, item);
            }
        }

        for(Map.Entry<Integer, Integer[]> entry: counter.entrySet()){
            String message = String.format("Rand by weight croupier select %d item weight %d times %d",
                    entry.getKey(), entry.getValue()[0], entry.getValue()[1]);
            System.out.println(message);
        }
    }

    @Test
    public void testRank() {
        Random random = new Random();
        Croupier<Double[]> croupier = Croupier.byRank(item -> item[1]);
        List<Double[]> data = IntStream.range(0, 100)
                .mapToObj(idx -> {
                    double value = random.nextDouble() * 10;
                    Double[] result = new Double[2];
                    result[0] = (double) idx;
                    result[1] = value;
                    return result;
                }).collect(Collectors.toList());

        Map<Double, Double[]> counter = new TreeMap<>();
        for(Double[] item: data){
            Double[] values = new Double[2];
            values[0] = item[1];
            values[1] = 0d;
            counter.put(item[0], values);
        }


        for (int times = 0; times < 10000; times++) {
            List<Double[]> sample = croupier.select(data, 10);
            Assertions.assertEquals(10, sample.size());
            for (Double[] element : sample) {
                double index = element[0];
                Double[] item = counter.get(index);
                item[1] = item[1] + 1;
                counter.put(index, item);
            }
        }

        for(Map.Entry<Double, Double[]> entry: counter.entrySet()){
            String message = String.format("Rand by weight croupier select %f item weight %f times %f",
                    entry.getKey(), entry.getValue()[0], entry.getValue()[1]);
            System.out.println(message);
        }
    }

    @Test
    public void testWeightZipper() {
        Random random = new Random();
        Croupier<Integer[]> croupier = Croupier.byZipScaled(item -> item[1]);
        List<Integer[]> data = IntStream.range(0, 1000)
                .mapToObj(idx -> {
                    int value = random.nextInt(10);
                    Integer[] result = new Integer[2];
                    result[0] = idx;
                    result[1] = value;
                    return result;
                }).collect(Collectors.toList());

        Map<Integer, Integer[]> counter = new TreeMap<>();
        for(Integer[] item: data){
            Integer[] values = new Integer[2];
            values[0] = item[1];
            values[1] = 0;
            counter.put(item[0], values);
        }


        for (int times = 0; times < 10000; times++) {
            List<Integer[]> sample = croupier.select(data, 10);
            Assertions.assertEquals(10, sample.size());
            for (Integer[] element : sample) {
                int index = element[0];
                Integer[] item = counter.get(index);
                item[1] = item[1] + 1;
                counter.put(index, item);
            }
        }

        for(Map.Entry<Integer, Integer[]> entry: counter.entrySet()){
            String message = String.format("Rand by weight croupier select %d item weight %d times %d",
                    entry.getKey(), entry.getValue()[0], entry.getValue()[1]);
            System.out.println(message);
        }
    }
}
