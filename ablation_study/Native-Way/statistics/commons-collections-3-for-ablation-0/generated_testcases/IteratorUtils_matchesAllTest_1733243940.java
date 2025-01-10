
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class IteratorUtils_matchesAllTest {

    @Test
    void testMatchesAll_AllElementsMatch() {
        Iterator<Integer> iterator = Arrays.asList(2, 4, 6).iterator();
        Predicate<Integer> predicate = n -> n % 2 == 0;
        assertTrue(IteratorUtils.matchesAll(iterator, predicate));
    }

    @Test
    void testMatchesAll_NotAllElementsMatch() {
        Iterator<Integer> iterator = Arrays.asList(2, 3, 6).iterator();
        Predicate<Integer> predicate = n -> n % 2 == 0;
        assertFalse(IteratorUtils.matchesAll(iterator, predicate));
    }

    @Test
    void testMatchesAll_NullIterator() {
        Predicate<Integer> predicate = n -> n % 2 == 0;
        assertTrue(IteratorUtils.matchesAll(null, predicate));
    }

    @Test
    void testMatchesAll_NullPredicate() {
        Iterator<Integer> iterator = Arrays.asList(2, 4, 6).iterator();
        assertThrows(NullPointerException.class, () -> IteratorUtils.matchesAll(iterator, null));
    }
}
