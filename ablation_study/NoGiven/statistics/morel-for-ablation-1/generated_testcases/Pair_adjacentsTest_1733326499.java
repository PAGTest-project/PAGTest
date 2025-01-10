
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Pair_adjacentsTest {

    @Test
    void testAdjacentsWithEmptyIterable() {
        Iterable<Pair<Integer, Integer>> result = Pair.adjacents(Collections.emptyList());
        assertTrue(!result.iterator().hasNext());
    }

    @Test
    void testAdjacentsWithNonEmptyIterable() {
        Iterable<Pair<Integer, Integer>> result = Pair.adjacents(Arrays.asList(1, 2, 3));
        Iterator<Pair<Integer, Integer>> iterator = result.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Pair.of(1, 2), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Pair.of(2, 3), iterator.next());
        assertTrue(!iterator.hasNext());
    }
}
