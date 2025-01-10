
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransformedSortedBag_transformedSortedBagTest {

    @Test
    void testTransformedSortedBagWithNonEmptyBag() {
        // Given
        SortedBag<String> bag = mock(SortedBag.class);
        Transformer<String, String> transformer = mock(Transformer.class);
        String[] values = {"value1", "value2"};
        when(bag.isEmpty()).thenReturn(false);
        when(bag.toArray()).thenReturn(values);
        when(transformer.apply("value1")).thenReturn("transformed1");
        when(transformer.apply("value2")).thenReturn("transformed2");

        // When
        TransformedSortedBag<String> result = TransformedSortedBag.transformedSortedBag(bag, transformer);

        // Then
        verify(bag).clear();
        verify(bag, times(2)).add(anyString());
        assertEquals(2, result.decorated().size());
    }

    @Test
    void testTransformedSortedBagWithEmptyBag() {
        // Given
        SortedBag<String> bag = mock(SortedBag.class);
        Transformer<String, String> transformer = mock(Transformer.class);
        when(bag.isEmpty()).thenReturn(true);

        // When
        TransformedSortedBag<String> result = TransformedSortedBag.transformedSortedBag(bag, transformer);

        // Then
        verify(bag, never()).clear();
        verify(bag, never()).add(anyString());
        assertEquals(0, result.decorated().size());
    }
}
