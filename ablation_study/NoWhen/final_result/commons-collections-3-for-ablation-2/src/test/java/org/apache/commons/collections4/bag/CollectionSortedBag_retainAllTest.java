
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CollectionSortedBag_retainAllTest {

    private CollectionSortedBag<String> bag;
    private SortedBag<String> mockBag;

    @BeforeEach
    public void setUp() {
        mockBag = Mockito.mock(SortedBag.class);
        bag = new CollectionSortedBag<>(mockBag);
    }

    @Test
    public void testRetainAll_NonEmptyCollection() {
        // Given
        Collection<String> coll = Arrays.asList("A", "B");
        Iterator<String> mockIterator = mock(Iterator.class);
        when(mockBag.iterator()).thenReturn(mockIterator);
        when(mockIterator.hasNext()).thenReturn(true, true, false);
        when(mockIterator.next()).thenReturn("A", "C");
        when(mockBag.contains("A")).thenReturn(true);
        when(mockBag.contains("C")).thenReturn(false);

        // When
        boolean result = bag.retainAll(coll);

        // Then
        assertTrue(result);
        verify(mockIterator).remove();
    }

    @Test
    public void testRetainAll_NullCollection() {
        // Given
        Collection<String> coll = null;

        // When
        boolean result = bag.retainAll(coll);

        // Then
        assertFalse(result);
        verify(mockBag).retainAll(null);
    }
}
