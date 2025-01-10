
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testNextWithElements() {
        assertEquals("a", loopingListIterator.next());
        assertEquals("b", loopingListIterator.next());
        assertEquals("c", loopingListIterator.next());
        assertEquals("a", loopingListIterator.next()); // Looping back to the start
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
    public void testNextWithReset() {
        assertEquals("a", loopingListIterator.next());
        assertEquals("b", loopingListIterator.next());
        loopingListIterator.reset();
        assertEquals("a", loopingListIterator.next());
    }

    @Test
    public void testNextWithHasNext() {
        assertTrue(loopingListIterator.hasNext());
        assertEquals("a", loopingListIterator.next());
        assertTrue(loopingListIterator.hasNext());
        assertEquals("b", loopingListIterator.next());
        assertTrue(loopingListIterator.hasNext());
        assertEquals("c", loopingListIterator.next());
        assertTrue(loopingListIterator.hasNext());
        assertEquals("a", loopingListIterator.next());
    }
}
