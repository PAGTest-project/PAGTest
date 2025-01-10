
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Pair_firstAndTest {

    @Test
    void testFirstAndWithNonEmptyIterable() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        Iterable<Pair<Integer, Integer>> result = Pair.firstAnd(list);
        Iterator<Pair<Integer, Integer>> iterator = result.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(new Pair<>(1, 2), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(new Pair<>(1, 3), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testFirstAndWithEmptyIterable() {
        List<Integer> list = Collections.emptyList();
        Iterable<Pair<Integer, Integer>> result = Pair.firstAnd(list);
        Iterator<Pair<Integer, Integer>> iterator = result.iterator();

        assertFalse(iterator.hasNext());
    }
}
