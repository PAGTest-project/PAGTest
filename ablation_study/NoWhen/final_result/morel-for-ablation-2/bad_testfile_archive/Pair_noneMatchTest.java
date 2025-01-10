
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Pair_noneMatchTest {

    @Test
    void testNoneMatch_NoMatch() {
        List<Integer> ks = Arrays.asList(1, 2, 3);
        List<Integer> vs = Arrays.asList(4, 5, 6);
        BiPredicate<Integer, Integer> predicate = (k, v) -> k > v;

        boolean result = Pair.noneMatch(ks, vs, predicate);

        assertTrue(result);
    }

    @Test
    void testNoneMatch_WithMatch() {
        List<Integer> ks = Arrays.asList(1, 2, 3);
        List<Integer> vs = Arrays.asList(4, 1, 6);
        BiPredicate<Integer, Integer> predicate = (k, v) -> k == v;

        boolean result = Pair.noneMatch(ks, vs, predicate);

        assertFalse(result);
    }

    @Test
    void testNoneMatch_DifferentLengths() {
        List<Integer> ks = Arrays.asList(1, 2, 3);
        List<Integer> vs = Arrays.asList(4, 1);
        BiPredicate<Integer, Integer> predicate = (k, v) -> k == v;

        boolean result = Pair.noneMatch(ks, vs, predicate);

        assertTrue(result);
    }
}
