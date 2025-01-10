
package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.ResettableIterator;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IteratorIterable_iteratorTest {

    @Test
    public void testIteratorWithResettableIterator() {
        // Given
        ResettableIterator<String> mockResettableIterator = mock(ResettableIterator.class);
        Iterator<String> mockTypeSafeIterator = mock(Iterator.class);
        IteratorIterable<String> iterable = new IteratorIterable<String>(mockResettableIterator) {
            @Override
            Iterator<String> createTypesafeIterator(Iterator<? extends String> iterator) {
                return mockTypeSafeIterator;
            }
        };

        // When
        Iterator<String> result = iterable.iterator();

        // Then
        verify(mockResettableIterator).reset();
        assertEquals(mockTypeSafeIterator, result);
    }

    @Test
    public void testIteratorWithoutResettableIterator() {
        // Given
        Iterator<String> mockIterator = mock(Iterator.class);
        Iterator<String> mockTypeSafeIterator = mock(Iterator.class);
        IteratorIterable<String> iterable = new IteratorIterable<String>(mockIterator) {
            @Override
            Iterator<String> createTypesafeIterator(Iterator<? extends String> iterator) {
                return mockTypeSafeIterator;
            }
        };

        // When
        Iterator<String> result = iterable.iterator();

        // Then
        assertEquals(mockTypeSafeIterator, result);
    }
}
