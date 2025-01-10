
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
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
        list = new ArrayList<>(Arrays.asList("a", "b", "c"));
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
        list.clear();
        assertThrows(NoSuchElementException.class, () -> loopingListIterator.next());
    }

    @Test
    public void testNextWithReset() {
        assertEquals("a", loopingListIterator.next());
        assertEquals("b", loopingListIterator.next());
        loopingListIterator.reset();
        assertEquals("a", loopingListIterator.next());
    }

    @Test
    public void testNextWithRemove() {
        assertEquals("a", loopingListIterator.next());
        loopingListIterator.remove();
        assertEquals("b", loopingListIterator.next());
        assertEquals("c", loopingListIterator.next());
        assertEquals("b", loopingListIterator.next()); // Loops back to the start
    }

    @Test
    public void testNextWithPrevious() {
        assertEquals("a", loopingListIterator.next());
        assertEquals("b", loopingListIterator.next());
        assertEquals("b", loopingListIterator.previous()); // Corrected to expect "b"
        assertEquals("a", loopingListIterator.previous());
        assertEquals("a", loopingListIterator.next()); // Corrected to expect "a"
    }

    @Test
    public void testNextWithNextIndex() {
        assertEquals("a", loopingListIterator.next());
        assertEquals(1, loopingListIterator.nextIndex());
        assertEquals("b", loopingListIterator.next());
        assertEquals(2, loopingListIterator.nextIndex());
        assertEquals("c", loopingListIterator.next());
        assertEquals(0, loopingListIterator.nextIndex()); // Loops back to the start
    }
}
