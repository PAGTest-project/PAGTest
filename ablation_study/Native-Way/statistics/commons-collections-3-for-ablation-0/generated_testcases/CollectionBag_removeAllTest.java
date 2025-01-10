
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;

public class CollectionBag_removeAllTest {

    @Test
    public void testRemoveAll_NonEmptyCollection() {
        Bag<String> mockBag = mock(Bag.class);
        when(mockBag.remove("element1", 1)).thenReturn(true);
        when(mockBag.remove("element2", 1)).thenReturn(false);
        when(mockBag.getCount("element1")).thenReturn(1);
        when(mockBag.getCount("element2")).thenReturn(1);

        CollectionBag<String> collectionBag = new CollectionBag<>(mockBag);
        Collection<String> coll = Arrays.asList("element1", "element2");

        boolean result = collectionBag.removeAll(coll);

        assertTrue(result);
        verify(mockBag, times(1)).remove("element1", 1);
        verify(mockBag, times(1)).remove("element2", 1);
    }

    @Test
    public void testRemoveAll_NullCollection() {
        Bag<String> mockBag = mock(Bag.class);
        when(mockBag.removeAll(null)).thenReturn(false);

        CollectionBag<String> collectionBag = new CollectionBag<>(mockBag);

        boolean result = collectionBag.removeAll(null);

        assertFalse(result);
        verify(mockBag, times(1)).removeAll(null);
    }
}
