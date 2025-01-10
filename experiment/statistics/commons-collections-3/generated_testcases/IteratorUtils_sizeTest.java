
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IteratorUtils_sizeTest {

    @Test
    public void testSizeWithNonNullIterator() {
        // Given
        Iterator<Object> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(true, true, false);
        when(iterator.next()).thenReturn(new Object(), new Object());

        // When
        int result = IteratorUtils.size(iterator);

        // Then
        assertEquals(2, result);
        verify(iterator, times(3)).hasNext();
        verify(iterator, times(2)).next();
    }

    @Test
    public void testSizeWithNullIterator() {
        // Given
        Iterator<Object> iterator = null;

        // When
        int result = IteratorUtils.size(iterator);

        // Then
        assertEquals(0, result);
    }

    @Test
    public void testSizeWithEmptyIterator() {
        // Given
        Iterator<Object> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(false);

        // When
        int result = IteratorUtils.size(iterator);

        // Then
        assertEquals(0, result);
        verify(iterator, times(1)).hasNext();
        verify(iterator, never()).next();
    }
}
