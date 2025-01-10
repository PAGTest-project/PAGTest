
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoopingListIterator_nextIndexTest {

    private LoopingListIterator<String> loop;

    @BeforeEach
    public void setUp() {
        List<String> list = Arrays.asList("a", "b", "c");
        loop = new LoopingListIterator<>(list);
    }

    @Test
    public void testNextIndexWithElements() {
        assertEquals(0, loop.nextIndex()); // <a> b c
        loop.next(); // a <b> c
        assertEquals(1, loop.nextIndex());
        loop.next(); // a b <c>
        assertEquals(2, loop.nextIndex());
        loop.next(); // <a> b c
        assertEquals(0, loop.nextIndex());
    }

    @Test
    public void testNextIndexWithEmptyList() {
        List<String> emptyList = new ArrayList<>();
        LoopingListIterator<String> emptyLoop = new LoopingListIterator<>(emptyList);
        assertThrows(NoSuchElementException.class, emptyLoop::nextIndex);
    }
}
