
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransformedSortedBag_transformedSortedBagTest {

    @Test
    public void testTransformedSortedBagWithNonEmptyBag() {
        // Given
        SortedBag<String> bag = mock(SortedBag.class);
        Transformer<String, String> transformer = mock(Transformer.class);
        when(bag.isEmpty()).thenReturn(false);
        when(bag.toArray()).thenReturn(new String[]{"a", "b"});
        when(transformer.apply("a")).thenReturn("A");
        when(transformer.apply("b")).thenReturn("B");

        // When
        TransformedSortedBag<String> result = TransformedSortedBag.transformedSortedBag(bag, transformer);

        // Then
        verify(bag).clear();
        verify(bag, times(2)).toArray();
        verify(transformer).apply("a");
        verify(transformer).apply("b");
        assertNotNull(result);
    }

    @Test
    public void testTransformedSortedBagWithEmptyBag() {
        // Given
        SortedBag<String> bag = mock(SortedBag.class);
        Transformer<String, String> transformer = mock(Transformer.class);
        when(bag.isEmpty()).thenReturn(true);

        // When
        TransformedSortedBag<String> result = TransformedSortedBag.transformedSortedBag(bag, transformer);

        // Then
        verify(bag, never()).clear();
        verify(bag, never()).toArray();
        verify(transformer, never()).apply(anyString());
        assertNotNull(result);
    }
}
