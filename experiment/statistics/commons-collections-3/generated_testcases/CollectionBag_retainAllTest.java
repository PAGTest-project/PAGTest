
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CollectionBag_retainAllTest {

    @Test
    public void testRetainAll_WithNonNullCollection() {
        // Given
        Bag<String> mockBag = Mockito.mock(Bag.class);
        CollectionBag<String> collectionBag = new CollectionBag<>(mockBag);
        Collection<String> collection = Arrays.asList("A", "B");

        Iterator<String> mockIterator = Mockito.mock(Iterator.class);
        when(mockBag.iterator()).thenReturn(mockIterator);
        when(mockIterator.hasNext()).thenReturn(true, true, false);
        when(mockIterator.next()).thenReturn("A", "C");

        // When
        boolean result = collectionBag.retainAll(collection);

        // Then
        assertTrue(result);
        verify(mockIterator).remove();
    }

    @Test
    public void testRetainAll_WithNullCollection() {
        // Given
        Bag<String> mockBag = Mockito.mock(Bag.class);
        CollectionBag<String> collectionBag = new CollectionBag<>(mockBag);

        // When
        boolean result = collectionBag.retainAll(null);

        // Then
        assertFalse(result);
        verify(mockBag).retainAll(null);
    }
}
