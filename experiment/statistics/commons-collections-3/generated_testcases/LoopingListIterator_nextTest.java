
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoopingListIterator_nextTest {

    private LoopingListIterator<String> loop;
    private List<String> list;

    @BeforeEach
    public void setUp() {
        list = Arrays.asList("a", "b", "c");
        loop = new LoopingListIterator<>(list);
    }

    @Test
    public void testNextWithElements() {
        assertEquals("a", loop.next());
        assertEquals("b", loop.next());
        assertEquals("c", loop.next());
        assertEquals("a", loop.next()); // Loops back to the start
    }

    @Test
    public void testNextWithEmptyList() {
        loop = new LoopingListIterator<>(Arrays.asList());
        assertThrows(NoSuchElementException.class, () -> {
            loop.next();
        });
    }
}
