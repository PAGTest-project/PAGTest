
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Pair_adjacentsTest {

    @Test
    void testAdjacentsWithNonEmptyIterable() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        Iterable<Pair<Integer, Integer>> result = Pair.adjacents(list);
        Iterator<Pair<Integer, Integer>> iterator = result.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(Pair.of(1, 2), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Pair.of(2, 3), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testAdjacentsWithEmptyIterable() {
        List<Integer> list = Collections.emptyList();
        Iterable<Pair<Integer, Integer>> result = Pair.adjacents(list);
        Iterator<Pair<Integer, Integer>> iterator = result.iterator();

        assertFalse(iterator.hasNext());
    }
}
