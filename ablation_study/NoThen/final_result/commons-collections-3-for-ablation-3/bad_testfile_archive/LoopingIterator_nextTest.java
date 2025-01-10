
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoopingIterator_nextTest {

    private LoopingIterator<String> loopingIterator;
    private List<String> collection;

    @BeforeEach
    public void setUp() {
        collection = Arrays.asList("A", "B", "C");
        loopingIterator = new LoopingIterator<>(collection);
    }

    @Test
    public void testNextWithElements() {
        assertEquals("A", loopingIterator.next());
        assertEquals("B", loopingIterator.next());
        assertEquals("C", loopingIterator.next());
        assertEquals("A", loopingIterator.next()); // Looping back to the start
    }

    @Test
    public void testNextWithEmptyCollection() {
        loopingIterator = new LoopingIterator<>(Arrays.asList());
        assertThrows(NoSuchElementException.class, () -> loopingIterator.next(),
                "NoSuchElementException was not thrown during next() call on empty collection.");
    }

    @Test
    public void testNextAfterReset() {
        assertEquals("A", loopingIterator.next());
        loopingIterator.reset();
        assertEquals("A", loopingIterator.next());
    }

    @Test
    public void testNextAfterRemove() {
        assertEquals("A", loopingIterator.next());
        collection = new ArrayList<>(collection); // Create a modifiable list
        collection.remove(0); // Remove element from the modifiable list
        loopingIterator = new LoopingIterator<>(collection); // Reinitialize the iterator
        assertEquals("B", loopingIterator.next());
    }
}
