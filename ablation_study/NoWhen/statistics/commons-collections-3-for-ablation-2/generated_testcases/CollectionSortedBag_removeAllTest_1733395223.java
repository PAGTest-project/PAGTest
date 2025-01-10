
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CollectionSortedBag_removeAllTest {

    private CollectionSortedBag<String> bag;
    private SortedBag<String> mockBag;

    @BeforeEach
    public void setUp() {
        mockBag = Mockito.mock(SortedBag.class);
        bag = new CollectionSortedBag<>(mockBag);
    }

    @Test
    public void testRemoveAll_NonEmptyCollection() {
        Collection<String> coll = Arrays.asList("A", "B", "C");
        when(mockBag.remove("A")).thenReturn(true);
        when(mockBag.remove("B")).thenReturn(false);
        when(mockBag.remove("C")).thenReturn(true);
        when(mockBag.getCount("A")).thenReturn(1);
        when(mockBag.getCount("B")).thenReturn(1);
        when(mockBag.getCount("C")).thenReturn(1);

        boolean result = bag.removeAll(coll);

        assertTrue(result);
    }

    @Test
    public void testRemoveAll_NullCollection() {
        when(mockBag.removeAll(null)).thenReturn(false);

        boolean result = bag.removeAll(null);

        assertFalse(result);
    }
}
