
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoopingListIterator_nextTest {

    private LoopingListIterator<String> loopingListIterator;
    private List<String> list;

    @BeforeEach
    public void setUp() {
        list = Arrays.asList("a", "b", "c");
        loopingListIterator = new LoopingListIterator<>(list);
    }

    @Test
    public void testNextWithNonEmptyList() {
        assertEquals("a", loopingListIterator.next());
        assertEquals("b", loopingListIterator.next());
        assertEquals("c", loopingListIterator.next());
        assertEquals("a", loopingListIterator.next()); // Loops back to the start
    }

    @Test
    public void testNextWithEmptyList() {
        list = Arrays.asList();
        loopingListIterator = new LoopingListIterator<>(list);
        assertThrows(NoSuchElementException.class, () -> {
            loopingListIterator.next();
        });
    }

    @Test
    public void testNextWithSingleElementList() {
        list = Arrays.asList("a");
        loopingListIterator = new LoopingListIterator<>(list);
        assertEquals("a", loopingListIterator.next());
        assertEquals("a", loopingListIterator.next()); // Loops back to the start
    }

    @Test
    public void testNextAfterReset() {
        assertEquals("a", loopingListIterator.next());
        loopingListIterator.reset();
        assertEquals("a", loopingListIterator.next());
    }

    @Test
    public void testNextAfterRemove() {
        assertEquals("a", loopingListIterator.next());
        loopingListIterator.remove();
        assertEquals("b", loopingListIterator.next());
    }

    @Test
    public void testNextAfterSet() {
        assertEquals("a", loopingListIterator.next());
        loopingListIterator.set("A");
        assertEquals("b", loopingListIterator.next());
        assertEquals("A", loopingListIterator.previous());
    }

    @Test
    public void testNextIndexAfterNext() {
        assertEquals("a", loopingListIterator.next());
        assertEquals(1, loopingListIterator.nextIndex());
    }

    @Test
    public void testPreviousAfterNext() {
        assertEquals("a", loopingListIterator.next());
        assertEquals("a", loopingListIterator.previous());
    }

    @Test
    public void testHasNextWithNonEmptyList() {
        assertTrue(loopingListIterator.hasNext());
    }

    @Test
    public void testHasNextWithEmptyList() {
        list = Arrays.asList();
        loopingListIterator = new LoopingListIterator<>(list);
        assertFalse(loopingListIterator.hasNext());
    }
}
