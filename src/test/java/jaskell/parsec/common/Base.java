package jaskell.parsec.common;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Mars Liu on 2016-01-09.
 * Base 为测试类提供共用的工具函数,继承它可以减少重复的代码书写.
 */
public abstract class Base {
    State<Character> newState(String data) {
        return new SimpleState<>(IntStream.range(0, data.length())
                .mapToObj(data::charAt)
                .collect(Collectors.toList()));
    }
}
