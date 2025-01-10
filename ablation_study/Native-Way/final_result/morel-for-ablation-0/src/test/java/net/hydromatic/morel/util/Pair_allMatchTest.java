
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Pair_allMatchTest {

    @Test
    void testAllMatch_True() {
        Iterable<Integer> ks = Arrays.asList(1, 2, 3);
        Iterable<Integer> vs = Arrays.asList(1, 2, 3);
        BiPredicate<Integer, Integer> predicate = Integer::equals;

        boolean result = Pair.allMatch(ks, vs, predicate);

        assertTrue(result);
    }

    @Test
    void testAllMatch_False() {
        Iterable<Integer> ks = Arrays.asList(1, 2, 3);
        Iterable<Integer> vs = Arrays.asList(1, 2, 4);
        BiPredicate<Integer, Integer> predicate = Integer::equals;

        boolean result = Pair.allMatch(ks, vs, predicate);

        assertFalse(result);
    }

    @Test
    void testAllMatch_DifferentLengths() {
        Iterable<Integer> ks = Arrays.asList(1, 2, 3);
        Iterable<Integer> vs = Arrays.asList(1, 2);
        BiPredicate<Integer, Integer> predicate = Integer::equals;

        boolean result = Pair.allMatch(ks, vs, predicate);

        assertTrue(result);
    }

    @Test
    void testAllMatch_EmptyIterables() {
        Iterable<Integer> ks = Collections.emptyList();
        Iterable<Integer> vs = Collections.emptyList();
        BiPredicate<Integer, Integer> predicate = Integer::equals;

        boolean result = Pair.allMatch(ks, vs, predicate);

        assertTrue(result);
    }
}
