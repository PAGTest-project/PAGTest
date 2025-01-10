
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
        SortedBag<Integer> bag = mock(SortedBag.class);
        Transformer<Integer, Integer> transformer = mock(Transformer.class);
        when(bag.isEmpty()).thenReturn(false);
        Integer[] values = {1, 2, 3};
        when(bag.toArray()).thenReturn(values);
        when(transformer.apply(anyInt())).thenAnswer(i -> (int) i.getArguments()[0] * 2);

        // When
        TransformedSortedBag<Integer> result = TransformedSortedBag.transformedSortedBag(bag, transformer);

        // Then
        verify(bag).clear();
        verify(bag).toArray();
        verify(transformer).apply(1);
        verify(transformer).apply(2);
        verify(transformer).apply(3);
        assertEquals(3, result.decorated().size()); // Assuming the decorated bag's add method adds transformed values
    }

    @Test
    public void testTransformedSortedBagWithEmptyBag() {
        // Given
        SortedBag<Integer> bag = mock(SortedBag.class);
        Transformer<Integer, Integer> transformer = mock(Transformer.class);
        when(bag.isEmpty()).thenReturn(true);

        // When
        TransformedSortedBag<Integer> result = TransformedSortedBag.transformedSortedBag(bag, transformer);

        // Then
        verify(bag, never()).toArray();
        verify(bag, never()).clear();
        verify(transformer, never()).apply(anyInt());
        assertEquals(0, result.decorated().size()); // Assuming the decorated bag is empty
    }
}
