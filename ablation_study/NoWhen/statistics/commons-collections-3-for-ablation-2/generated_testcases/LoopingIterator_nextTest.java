
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoopingIterator_nextTest {

    private LoopingIterator<String> loopingIterator;
    private List<String> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        loopingIterator = new LoopingIterator<>(list);
    }

    @Test
    public void testNextWithElements() {
        assertEquals("a", loopingIterator.next(), "1st next should return 'a'");
        assertEquals("b", loopingIterator.next(), "2nd next should return 'b'");
        assertEquals("c", loopingIterator.next(), "3rd next should return 'c'");
        assertEquals("a", loopingIterator.next(), "4th next should return 'a' after looping");
    }

    @Test
    public void testNextWithEmptyCollection() {
        loopingIterator = new LoopingIterator<>(new ArrayList<>());
        assertThrows(NoSuchElementException.class, () -> loopingIterator.next(), "next should throw NoSuchElementException for empty collection");
    }

    @Test
    public void testNextWithReset() {
        assertEquals("a", loopingIterator.next(), "1st next should return 'a'");
        assertEquals("b", loopingIterator.next(), "2nd next should return 'b'");
        loopingIterator.reset();
        assertEquals("a", loopingIterator.next(), "next should return 'a' after reset");
    }

    @Test
    public void testNextWithRemove() {
        assertEquals("a", loopingIterator.next(), "1st next should return 'a'");
        loopingIterator.remove();
        assertEquals("b", loopingIterator.next(), "2nd next should return 'b' after removal");
    }

    @Test
    public void testNextWithSize() {
        assertEquals(3, loopingIterator.size(), "size should return 3 initially");
        assertEquals("a", loopingIterator.next(), "1st next should return 'a'");
        assertEquals(3, loopingIterator.size(), "size should still return 3 after next call");
    }
}
