
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
        Collection<String> collection = Arrays.asList("A", "B");
        when(mockBag.iterator()).thenReturn(Arrays.asList("A", "B", "C").iterator());
        when(mockBag.contains("A")).thenReturn(true);
        when(mockBag.contains("B")).thenReturn(true);
        when(mockBag.contains("C")).thenReturn(false);

        // When
        boolean result = bag.retainAll(collection);

        // Then
        assertTrue(result);
        verify(mockBag, times(1)).iterator();
        verify(mockBag, times(1)).contains("A");
        verify(mockBag, times(1)).contains("B");
        verify(mockBag, times(1)).contains("C");
        verify(mockBag, times(1)).remove(any());
    }

    @Test
    public void testRetainAll_NullCollection() {
        // Given
        Collection<String> collection = null;

        // When
        boolean result = bag.retainAll(collection);

        // Then
        assertFalse(result);
        verify(mockBag, times(1)).retainAll(null);
    }
}
