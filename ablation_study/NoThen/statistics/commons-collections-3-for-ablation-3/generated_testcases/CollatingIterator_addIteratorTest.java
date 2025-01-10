
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollatingIterator_addIteratorTest {

    private CollatingIterator<Integer> collatingIterator;

    @BeforeEach
    void setUp() {
        collatingIterator = new CollatingIterator<>(Comparator.naturalOrder());
    }

    @Test
    void testAddIterator_Success() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3).iterator();
        collatingIterator.addIterator(iterator);
        assertEquals(1, collatingIterator.getIterators().size());
    }

    @Test
    void testAddIterator_NullIterator() {
        assertThrows(NullPointerException.class, () -> collatingIterator.addIterator(null));
    }

    @Test
    void testAddIterator_AfterStart() {
        collatingIterator.hasNext(); // This will call start() internally
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3).iterator();
        assertThrows(IllegalStateException.class, () -> collatingIterator.addIterator(iterator));
    }
}
