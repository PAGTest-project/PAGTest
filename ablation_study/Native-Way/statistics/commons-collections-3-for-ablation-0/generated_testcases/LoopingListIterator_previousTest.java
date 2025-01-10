
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoopingListIterator_previousTest {

    private LoopingListIterator<String> loopingListIterator;
    private List<String> list;

    @BeforeEach
    public void setUp() {
        list = Arrays.asList("a", "b", "c");
        loopingListIterator = new LoopingListIterator<>(list);
    }

    @Test
    public void testPreviousWithEmptyList() {
        loopingListIterator = new LoopingListIterator<>(Arrays.asList());
        assertThrows(NoSuchElementException.class, () -> {
            loopingListIterator.previous();
        });
    }

    @Test
    public void testPreviousAtBeginning() {
        assertEquals("c", loopingListIterator.previous());
    }

    @Test
    public void testPreviousAtEnd() {
        loopingListIterator.next();
        loopingListIterator.next();
        loopingListIterator.next();
        assertEquals("c", loopingListIterator.previous());
    }

    @Test
    public void testPreviousInMiddle() {
        loopingListIterator.next();
        assertEquals("a", loopingListIterator.previous());
    }

    @Test
    public void testPreviousAfterReset() {
        loopingListIterator.reset();
        assertEquals("c", loopingListIterator.previous());
    }
}
