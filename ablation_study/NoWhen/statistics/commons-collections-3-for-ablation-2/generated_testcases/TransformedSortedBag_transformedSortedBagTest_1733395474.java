
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransformedSortedBag_transformedSortedBagTest {

    @Test
    void testTransformedSortedBag_NonEmptyBag() {
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
        SortedBag<Integer> decoratedBag = result.getSortedBag();
        assertEquals(3, decoratedBag.size()); // 1*2 + 2*2 + 3*2 = 6
    }

    @Test
    void testTransformedSortedBag_EmptyBag() {
        // Given
        SortedBag<Integer> bag = mock(SortedBag.class);
        Transformer<Integer, Integer> transformer = mock(Transformer.class);
        when(bag.isEmpty()).thenReturn(true);

        // When
        TransformedSortedBag<Integer> result = TransformedSortedBag.transformedSortedBag(bag, transformer);

        // Then
        verify(bag, never()).clear();
        SortedBag<Integer> decoratedBag = result.getSortedBag();
        assertTrue(decoratedBag.isEmpty());
    }
}
