
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Pair_anyMatchTest {

    @Test
    void testAnyMatch_True() {
        List<Integer> ks = Arrays.asList(1, 2, 3);
        List<Integer> vs = Arrays.asList(1, 2, 3);
        BiPredicate<Integer, Integer> predicate = (k, v) -> k.equals(v);

        assertTrue(Pair.anyMatch(ks, vs, predicate));
    }

    @Test
    void testAnyMatch_False() {
        List<Integer> ks = Arrays.asList(1, 2, 3);
        List<Integer> vs = Arrays.asList(4, 5, 6);
        BiPredicate<Integer, Integer> predicate = (k, v) -> k.equals(v);

        assertFalse(Pair.anyMatch(ks, vs, predicate));
    }

    @Test
    void testAnyMatch_DifferentLengths() {
        List<Integer> ks = Arrays.asList(1, 2, 3);
        List<Integer> vs = Arrays.asList(1, 2);
        BiPredicate<Integer, Integer> predicate = (k, v) -> k.equals(v);

        assertTrue(Pair.anyMatch(ks, vs, predicate));
    }

    @Test
    void testAnyMatch_EmptyIterables() {
        List<Integer> ks = Collections.emptyList();
        List<Integer> vs = Collections.emptyList();
        BiPredicate<Integer, Integer> predicate = (k, v) -> k.equals(v);

        assertFalse(Pair.anyMatch(ks, vs, predicate));
    }
}
