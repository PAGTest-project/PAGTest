
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Pair_allMatchTest {

    @Test
    void testAllMatch_AllPairsMatch() {
        List<Integer> ks = Arrays.asList(1, 2, 3);
        List<Integer> vs = Arrays.asList(1, 2, 3);
        BiPredicate<Integer, Integer> predicate = (k, v) -> k.equals(v);

        boolean result = Pair.allMatch(ks, vs, predicate);

        assertTrue(result);
    }

    @Test
    void testAllMatch_NotAllPairsMatch() {
        List<Integer> ks = Arrays.asList(1, 2, 3);
        List<Integer> vs = Arrays.asList(1, 2, 4);
        BiPredicate<Integer, Integer> predicate = (k, v) -> k.equals(v);

        boolean result = Pair.allMatch(ks, vs, predicate);

        assertFalse(result);
    }
}
