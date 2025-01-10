
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoundedIterator_removeTest {

    private List<String> testList;

    @BeforeEach
    public void setUp() {
        testList = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
    }

    @Test
    public void testRemoveBeforeNext() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 1, 10);

        // Ensure that calling remove before next throws IllegalStateException
        assertThrows(IllegalStateException.class, () -> iter.remove());
    }

    @Test
    public void testRemoveAfterNext() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 1, 10);

        // Call next to advance the iterator
        assertTrue(iter.hasNext());
        assertEquals("b", iter.next());

        // Now remove should work without throwing an exception
        assertThrows(UnsupportedOperationException.class, () -> iter.remove());
    }
}
