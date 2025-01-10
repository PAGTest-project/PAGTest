
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Pair_firstAndTest {

    @Test
    void testFirstAndWithEmptyIterable() {
        Iterable<Integer> emptyIterable = Collections.emptyList();
        Iterable<Pair<Integer, Integer>> result = Pair.firstAnd(emptyIterable);
        Iterator<Pair<Integer, Integer>> iterator = result.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testFirstAndWithNonEmptyIterable() {
        List<Integer> nonEmptyIterable = Arrays.asList(1, 2, 3);
        Iterable<Pair<Integer, Integer>> result = Pair.firstAnd(nonEmptyIterable);
        Iterator<Pair<Integer, Integer>> iterator = result.iterator();
        assertTrue(iterator.hasNext());
        Pair<Integer, Integer> firstPair = iterator.next();
        assertEquals(1, firstPair.left);
        assertEquals(2, firstPair.right);
    }
}
