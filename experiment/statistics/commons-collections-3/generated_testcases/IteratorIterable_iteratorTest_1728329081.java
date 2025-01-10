
package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.ResettableIterator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Iterator;

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
        assertEquals(iterable.typeSafeIterator, result);
    }

    @Test
    public void testIteratorWithNonResettableIterator() {
        // Given
        Iterator<String> mockIterator = mock(Iterator.class);
        IteratorIterable<String> iterable = new IteratorIterable<>(mockIterator);

        // When
        Iterator<String> result = iterable.iterator();

        // Then
        assertEquals(iterable.typeSafeIterator, result);
    }
}
