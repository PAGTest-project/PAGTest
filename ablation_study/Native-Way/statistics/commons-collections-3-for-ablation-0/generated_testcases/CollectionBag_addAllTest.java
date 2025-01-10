
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;

public class CollectionBag_addAllTest {

    @Test
    public void testAddAll() {
        Bag<String> mockBag = mock(Bag.class);
        CollectionBag<String> collectionBag = new CollectionBag<>(mockBag);

        Collection<String> coll = Arrays.asList("A", "B", "C");

        when(mockBag.add("A", 1)).thenReturn(true);
        when(mockBag.add("B", 1)).thenReturn(false);
        when(mockBag.add("C", 1)).thenReturn(true);

        boolean result = collectionBag.addAll(coll);

        assertTrue(result);
        verify(mockBag, times(1)).add("A", 1);
        verify(mockBag, times(1)).add("B", 1);
        verify(mockBag, times(1)).add("C", 1);
    }
}
