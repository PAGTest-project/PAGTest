
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CollectionSortedBag_addAllTest {

    @Test
    public void testAddAll_WithNonEmptyCollection() {
        // Given
        SortedBag<String> mockBag = mock(SortedBag.class);
        CollectionSortedBag<String> bag = new CollectionSortedBag<>(mockBag);
        when(mockBag.add(anyString(), eq(1))).thenReturn(true);

        // When
        boolean result = bag.addAll(Arrays.asList("a", "b", "c"));

        // Then
        assertTrue(result);
        verify(mockBag, times(3)).add(anyString(), eq(1));
    }

    @Test
    public void testAddAll_WithEmptyCollection() {
        // Given
        SortedBag<String> mockBag = mock(SortedBag.class);
        CollectionSortedBag<String> bag = new CollectionSortedBag<>(mockBag);

        // When
        boolean result = bag.addAll(Collections.emptyList());

        // Then
        assertFalse(result);
        verify(mockBag, never()).add(anyString(), anyInt());
    }
}
