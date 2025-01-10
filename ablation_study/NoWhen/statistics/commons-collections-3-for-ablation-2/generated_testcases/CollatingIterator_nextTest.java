
package org.apache.commons.collections4.iterators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CollatingIterator_nextTest {

    private CollatingIterator<Integer> collatingIterator;
    private Comparator<Integer> comparator;
    private List<Iterator<Integer>> iterators;

    @BeforeEach
    void setUp() {
        comparator = Comparator.naturalOrder();
        iterators = new ArrayList<>();
        collatingIterator = new CollatingIterator<>(comparator, 2);
    }

    @Test
    void testNext_NoSuchElementException() {
        Iterator<Integer> emptyIterator = Collections.emptyIterator();
        collatingIterator.addIterator(emptyIterator);
        collatingIterator.addIterator(emptyIterator);

        assertThrows(NoSuchElementException.class, () -> collatingIterator.next());
    }

    @Test
    void testNext_Success() {
        Iterator<Integer> iterator1 = Arrays.asList(1, 3).iterator();
        Iterator<Integer> iterator2 = Arrays.asList(2, 4).iterator();
        collatingIterator.addIterator(iterator1);
        collatingIterator.addIterator(iterator2);

        assertEquals(1, collatingIterator.next());
        assertEquals(2, collatingIterator.next());
        assertEquals(3, collatingIterator.next());
        assertEquals(4, collatingIterator.next());
    }
}
