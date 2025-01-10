
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoopingIterator_nextTest {

    private LoopingIterator<String> loop;
    private List<String> list;

    @BeforeEach
    public void setUp() {
        list = Arrays.asList("a", "b", "c");
        loop = new LoopingIterator<>(list);
    }

    @Test
    public void testNextWithElements() {
        assertEquals("a", loop.next());
        assertEquals("b", loop.next());
        assertEquals("c", loop.next());
        assertEquals("a", loop.next()); // Loops back to the start
    }

    @Test
    public void testNextWithEmptyCollection() {
        loop = new LoopingIterator<>(Arrays.asList());
        assertThrows(NoSuchElementException.class, () -> loop.next());
    }
}
