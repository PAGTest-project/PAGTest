
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

public class CollectionBag_addAllTest {

    private Bag<String> mockBag;
    private CollectionBag<String> collectionBag;

    @BeforeEach
    public void setUp() {
        mockBag = Mockito.mock(Bag.class);
        collectionBag = new CollectionBag<>(mockBag);
    }

    @Test
    public void testAddAll_NoChange() {
        Collection<String> coll = Arrays.asList("A", "B");
        when(mockBag.add("A", 1)).thenReturn(false);
        when(mockBag.add("B", 1)).thenReturn(false);

        boolean result = collectionBag.addAll(coll);

        assertFalse(result);
    }

    @Test
    public void testAddAll_WithChange() {
        Collection<String> coll = Arrays.asList("A", "B");
        when(mockBag.add("A", 1)).thenReturn(true);
        when(mockBag.add("B", 1)).thenReturn(false); // Changed to return false

        boolean result = collectionBag.addAll(coll);

        assertTrue(result);
    }
}
