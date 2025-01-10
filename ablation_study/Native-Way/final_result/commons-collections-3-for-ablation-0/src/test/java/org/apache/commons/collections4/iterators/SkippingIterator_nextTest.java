
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SkippingIterator_nextTest {

    private List<String> testList;

    @SuppressWarnings("unchecked")
    @BeforeEach
    public void setUp() throws Exception {
        testList = Arrays.asList("a", "b", "c", "d");
    }

    @Test
    public void testNextWithOffset() {
        final Iterator<String> iter = new SkippingIterator<>(testList.iterator(), 2);
        assertTrue(iter.hasNext());
        assertEquals("c", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("d", iter.next());
        assertThrows(NoSuchElementException.class, () -> iter.next(),
                "Expected NoSuchElementException.");
    }

    @Test
    public void testNextWithoutOffset() {
        final Iterator<String> iter = new SkippingIterator<>(testList.iterator(), 0);
        assertTrue(iter.hasNext());
        assertEquals("a", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("b", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("c", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("d", iter.next());
        assertThrows(NoSuchElementException.class, () -> iter.next(),
                "Expected NoSuchElementException.");
    }

    @Test
    public void testNextWithOffsetGreaterThanSize() {
        final Iterator<String> iter = new SkippingIterator<>(testList.iterator(), 5);
        assertThrows(NoSuchElementException.class, () -> iter.next(),
                "Expected NoSuchElementException.");
    }
}
