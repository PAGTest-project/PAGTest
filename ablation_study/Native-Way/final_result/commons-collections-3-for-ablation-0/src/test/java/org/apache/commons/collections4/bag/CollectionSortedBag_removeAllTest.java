
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CollectionSortedBag_removeAllTest {

    @Test
    public void testRemoveAll_NonEmptyCollection() {
        SortedBag<String> mockBag = Mockito.mock(SortedBag.class);
        when(mockBag.remove("element1", 1)).thenReturn(true);
        when(mockBag.remove("element2", 1)).thenReturn(false);
        when(mockBag.getCount("element1")).thenReturn(1);
        when(mockBag.getCount("element2")).thenReturn(1);

        CollectionSortedBag<String> bag = new CollectionSortedBag<>(mockBag);
        Collection<String> coll = Arrays.asList("element1", "element2");

        boolean result = bag.removeAll(coll);

        assertTrue(result);
    }

    @Test
    public void testRemoveAll_NullCollection() {
        SortedBag<String> mockBag = Mockito.mock(SortedBag.class);
        when(mockBag.removeAll(null)).thenReturn(false);

        CollectionSortedBag<String> bag = new CollectionSortedBag<>(mockBag);

        boolean result = bag.removeAll(null);

        assertFalse(result);
    }
}
