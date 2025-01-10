
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CollectionBag_removeAllTest {

    private CollectionBag<String> bag;
    private Bag<String> decoratedBag;

    @BeforeEach
    public void setUp() {
        decoratedBag = Mockito.mock(Bag.class);
        bag = new CollectionBag<>(decoratedBag);
    }

    @Test
    public void testRemoveAll_WithNonNullCollection() {
        Collection<String> coll = Arrays.asList("A", "B", "C");
        when(decoratedBag.remove("A", 1)).thenReturn(true);
        when(decoratedBag.remove("B", 1)).thenReturn(false);
        when(decoratedBag.remove("C", 1)).thenReturn(true);
        when(decoratedBag.getCount("A")).thenReturn(1);
        when(decoratedBag.getCount("B")).thenReturn(1);
        when(decoratedBag.getCount("C")).thenReturn(1);

        boolean result = bag.removeAll(coll);

        assertTrue(result);
        verify(decoratedBag, times(1)).remove("A", 1);
        verify(decoratedBag, times(1)).remove("B", 1);
        verify(decoratedBag, times(1)).remove("C", 1);
    }

    @Test
    public void testRemoveAll_WithNullCollection() {
        when(decoratedBag.removeAll(null)).thenReturn(true);

        boolean result = bag.removeAll(null);

        assertTrue(result);
        verify(decoratedBag, times(1)).removeAll(null);
    }
}
