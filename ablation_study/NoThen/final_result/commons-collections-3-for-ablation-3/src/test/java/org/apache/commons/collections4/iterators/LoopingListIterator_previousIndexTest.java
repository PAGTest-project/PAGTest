
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoopingListIterator_previousIndexTest {

    private LoopingListIterator<String> loopingListIterator;
    private List<String> list;

    @BeforeEach
    public void setUp() {
        list = Arrays.asList("a", "b", "c");
        loopingListIterator = new LoopingListIterator<>(list);
    }

    @Test
    public void testPreviousIndexWithEmptyList() {
        loopingListIterator = new LoopingListIterator<>(Arrays.asList());
        assertThrows(NoSuchElementException.class, () -> {
            loopingListIterator.previousIndex();
        });
    }

    @Test
    public void testPreviousIndexAtBeginning() {
        assertEquals(2, loopingListIterator.previousIndex());
    }

    @Test
    public void testPreviousIndexAfterPrevious() {
        loopingListIterator.previous();
        assertEquals(1, loopingListIterator.previousIndex());
    }

    @Test
    public void testPreviousIndexAfterReset() {
        loopingListIterator.reset();
        assertEquals(2, loopingListIterator.previousIndex());
    }
}
