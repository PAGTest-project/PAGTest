
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjectGraphIterator_removeTest {

    private ObjectGraphIterator<String> iterator;
    private List<String> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        iterator = new ObjectGraphIterator<>(list.iterator());
    }

    @Test
    public void testRemoveSuccess() {
        // Given
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());

        // When
        iterator.remove();

        // Then
        assertFalse(list.contains("A"));
        assertEquals(2, list.size());
    }

    @Test
    public void testRemoveWithoutNext() {
        // Given
        assertTrue(iterator.hasNext());

        // When/Then
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }

    @Test
    public void testRemoveTwice() {
        // Given
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        iterator.remove();

        // When/Then
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }

    @Test
    public void testRemoveAfterIteration() {
        // Given
        while (iterator.hasNext()) {
            iterator.next();
        }

        // When/Then
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }
}
