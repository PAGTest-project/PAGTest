
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        testList = Arrays.asList((String[]) new String[]{"a", "b", "c", "d", "e", "f", "g"});
    }

    @Test
    public void testNextAfterSkipping() {
        final Iterator<String> iter = new SkippingIterator<>(testList.iterator(), 3);

        assertTrue(iter.hasNext());
        assertEquals("d", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("e", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("f", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("g", iter.next());

        assertFalse(iter.hasNext());

        assertThrows(NoSuchElementException.class, () -> iter.next(),
                "Expected NoSuchElementException.");
    }

    @Test
    public void testNextWithoutSkipping() {
        final Iterator<String> iter = new SkippingIterator<>(testList.iterator(), 0);

        assertTrue(iter.hasNext());
        assertEquals("a", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("b", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("c", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("d", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("e", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("f", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("g", iter.next());

        assertFalse(iter.hasNext());

        assertThrows(NoSuchElementException.class, () -> iter.next(),
                "Expected NoSuchElementException.");
    }
}
