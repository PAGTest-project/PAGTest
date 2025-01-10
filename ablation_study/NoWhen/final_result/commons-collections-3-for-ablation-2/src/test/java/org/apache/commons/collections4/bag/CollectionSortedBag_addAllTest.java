
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CollectionSortedBag_addAllTest {

    private SortedBag<String> mockBag;
    private CollectionSortedBag<String> collectionSortedBag;

    @BeforeEach
    public void setUp() {
        mockBag = mock(SortedBag.class);
        collectionSortedBag = new CollectionSortedBag<>(mockBag);
    }

    @Test
    public void testAddAll() {
        Collection<String> coll = Arrays.asList("A", "B", "C");

        when(mockBag.add("A", 1)).thenReturn(true);
        when(mockBag.add("B", 1)).thenReturn(true);
        when(mockBag.add("C", 1)).thenReturn(true);

        boolean result = collectionSortedBag.addAll(coll);

        assertTrue(result);
        verify(mockBag, times(1)).add("A", 1);
        verify(mockBag, times(1)).add("B", 1);
        verify(mockBag, times(1)).add("C", 1);
    }
}
