
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

public class LoopingIterator_nextTest {

    private LoopingIterator<String> loop;
    private List<String> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        loop = new LoopingIterator<>(list);
    }

    @Test
    public void testNextWithElements() {
        assertEquals("a", loop.next());
        assertEquals("b", loop.next());
        assertEquals("c", loop.next());
        assertEquals("a", loop.next()); // Looping back to the start
    }

    @Test
    public void testNextWithEmptyCollection() {
        loop = new LoopingIterator<>(new ArrayList<>());
        assertThrows(NoSuchElementException.class, () -> {
            loop.next();
        });
    }

    @Test
    public void testNextWithReset() {
        assertEquals("a", loop.next());
        assertEquals("b", loop.next());
        loop.reset();
        assertEquals("a", loop.next());
    }

    @Test
    public void testNextWithRemove() {
        assertEquals("a", loop.next());
        loop.remove();
        assertEquals(2, loop.size());
        assertEquals("b", loop.next());
        assertEquals("c", loop.next());
        assertEquals("b", loop.next()); // Looping back to the start
    }
}
