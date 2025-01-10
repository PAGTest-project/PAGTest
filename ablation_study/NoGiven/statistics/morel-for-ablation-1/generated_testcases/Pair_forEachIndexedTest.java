
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Pair_forEachIndexedTest {

    @Test
    void testForEachIndexed() {
        List<String> ks = Arrays.asList("a", "b", "c");
        List<Integer> vs = Arrays.asList(1, 2, 3);
        StringBuilder result = new StringBuilder();

        Pair.forEachIndexed(ks, vs, (index, k, v) -> result.append(index).append(k).append(v));

        assertEquals("0a11b22c3", result.toString());
    }

    @Test
    void testForEachIndexedWithDifferentLengths() {
        List<String> ks = Arrays.asList("a", "b");
        List<Integer> vs = Arrays.asList(1, 2, 3);
        StringBuilder result = new StringBuilder();

        Pair.forEachIndexed(ks, vs, (index, k, v) -> result.append(index).append(k).append(v));

        assertEquals("0a11b2", result.toString());
    }

    @Test
    void testForEachIndexedWithEmptyLists() {
        List<String> ks = Collections.emptyList();
        List<Integer> vs = Collections.emptyList();
        StringBuilder result = new StringBuilder();

        Pair.forEachIndexed(ks, vs, (index, k, v) -> result.append(index).append(k).append(v));

        assertEquals("", result.toString());
    }
}
