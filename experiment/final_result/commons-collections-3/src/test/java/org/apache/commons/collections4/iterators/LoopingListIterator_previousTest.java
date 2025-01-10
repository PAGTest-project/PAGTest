
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoopingListIterator_previousTest {

    private LoopingListIterator<String> loop;

    @BeforeEach
    public void setUp() {
        final List<String> list = Arrays.asList("a", "b", "c");
        loop = new LoopingListIterator<>(list);
    }

    @Test
    public void testPreviousWithElements() {
        // Move to the end of the list
        while (loop.hasNext()) {
            loop.next();
        }

        // Test previous()
        assertEquals("c", loop.previous());
        assertEquals("b", loop.previous());
        assertEquals("a", loop.previous());
    }

    @Test
    public void testPreviousAtStart() {
        // Test previous() at the start of the list
        assertEquals("c", loop.previous());
    }

    @Test
    public void testPreviousOnEmptyList() {
        final List<String> emptyList = Arrays.asList();
        loop = new LoopingListIterator<>(emptyList);

        // Test previous() on an empty list
        assertThrows(NoSuchElementException.class, () -> {
            loop.previous();
        });
    }
}
