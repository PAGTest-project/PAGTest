
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CollectionBag_retainAllTest {

    private CollectionBag<String> bag;
    private Bag<String> decoratedBag;

    @BeforeEach
    public void setUp() {
        decoratedBag = Mockito.mock(Bag.class);
        bag = new CollectionBag<>(decoratedBag);
    }

    @Test
    public void testRetainAll_NonEmptyCollection() {
        // Given
        Collection<String> coll = Arrays.asList("A", "B");
        Iterator<String> iterator = mock(Iterator.class);
        when(decoratedBag.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, true, false);
        when(iterator.next()).thenReturn("A", "C");
        when(decoratedBag.contains("A")).thenReturn(true);
        when(decoratedBag.contains("C")).thenReturn(false);

        // When
        boolean result = bag.retainAll(coll);

        // Then
        assertTrue(result);
        verify(iterator).remove();
    }

    @Test
    public void testRetainAll_NullCollection() {
        // When
        boolean result = bag.retainAll(null);

        // Then
        assertFalse(result);
        verify(decoratedBag).retainAll(null);
    }
}
