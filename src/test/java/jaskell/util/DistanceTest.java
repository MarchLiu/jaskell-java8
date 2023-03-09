package jaskell.util;

import org.junit.jupiter.api.Test;

import static jaskell.util.Distance.levenshtein;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceTest {
    @Test
    public void testLevenshtein() {
        assertEquals(1, levenshtein("126.com", "127.com"));
        assertEquals(1, levenshtein("127.com", "126.com"));
        assertEquals(0, levenshtein("hotmail.com", "hotmail.com"));
        assertEquals(0, levenshtein("", ""));
        assertEquals(8, levenshtein("sina.com", ""));
        assertEquals(9, levenshtein("gmail.com", ""));
        assertEquals(2, levenshtein("sina.com", "sina.cn"));
        assertEquals(1, levenshtein("139.com", ".139.com"));
        assertEquals(3, levenshtein("qq.", "qq.com"));
        assertEquals(1, levenshtein("gmail.com", "gmail.com."));
        assertEquals(1, levenshtein(".gmail.com", "gmail.com"));
        assertEquals(10, levenshtein("ttttattttctg", "tcaaccctaccat"));
    }
}
