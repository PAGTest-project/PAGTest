
package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.ResettableIterator;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class IteratorIterable_iteratorTest {

    @Test
    public void testIteratorWithResettableIterator() {
        // Given
        ResettableIterator<String> mockResettableIterator = mock(ResettableIterator.class);
        IteratorIterable<String> iterable = new IteratorIterable<>(mockResettableIterator);

        // When
        Iterator<String> result = iterable.iterator();

        // Then
        verify(mockResettableIterator).reset();
        assertTrue(result instanceof Iterator);
    }

    @Test
    public void testIteratorWithoutResettableIterator() {
        // Given
        Iterator<String> mockIterator = mock(Iterator.class);
        IteratorIterable<String> iterable = new IteratorIterable<>(mockIterator);

        // When
        Iterator<String> result = iterable.iterator();

        // Then
        assertTrue(result instanceof Iterator);
    }
}
