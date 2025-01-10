
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoopingListIterator_nextIndexTest {

    private LoopingListIterator<String> loopingListIterator;
    private List<String> list;

    @BeforeEach
    public void setUp() {
        list = Arrays.asList("a", "b", "c");
        loopingListIterator = new LoopingListIterator<>(list);
    }

    @Test
    public void testNextIndexWithElements() {
        assertEquals(0, loopingListIterator.nextIndex());
        loopingListIterator.next();
        assertEquals(1, loopingListIterator.nextIndex());
        loopingListIterator.next();
        assertEquals(2, loopingListIterator.nextIndex());
        loopingListIterator.next();
        assertEquals(0, loopingListIterator.nextIndex());
    }

    @Test
    public void testNextIndexWithEmptyList() {
        list = Arrays.asList();
        loopingListIterator = new LoopingListIterator<>(list);
        assertThrows(NoSuchElementException.class, () -> {
            loopingListIterator.nextIndex();
        });
    }

    @Test
    public void testNextIndexAfterReset() {
        loopingListIterator.reset();
        assertEquals(0, loopingListIterator.nextIndex());
    }

    @Test
    public void testNextIndexAfterFullIteration() {
        loopingListIterator.next();
        loopingListIterator.next();
        loopingListIterator.next();
        assertEquals(0, loopingListIterator.nextIndex());
    }
}
