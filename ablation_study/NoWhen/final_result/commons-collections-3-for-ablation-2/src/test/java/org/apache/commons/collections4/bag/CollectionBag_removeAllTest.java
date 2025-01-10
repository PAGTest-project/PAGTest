
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CollectionBag_removeAllTest {

    private Bag<String> mockBag;
    private CollectionBag<String> collectionBag;

    @BeforeEach
    public void setUp() {
        mockBag = Mockito.mock(Bag.class);
        collectionBag = new CollectionBag<>(mockBag);
    }

    @Test
    public void testRemoveAll_NonEmptyCollection() {
        Collection<String> coll = Arrays.asList("A", "B", "C");
        when(mockBag.remove("A", 1)).thenReturn(true);
        when(mockBag.remove("B", 1)).thenReturn(false);
        when(mockBag.remove("C", 1)).thenReturn(true);
        when(mockBag.getCount("A")).thenReturn(1);
        when(mockBag.getCount("B")).thenReturn(1);
        when(mockBag.getCount("C")).thenReturn(1);

        boolean result = collectionBag.removeAll(coll);

        assertTrue(result);
    }

    @Test
    public void testRemoveAll_NullCollection() {
        boolean result = collectionBag.removeAll(null);

        assertFalse(result);
    }
}
