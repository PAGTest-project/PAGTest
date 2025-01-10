
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PredicatedBag_addTest {

    @Test
    public void testAddValidObject() {
        // Given
        Bag<String> mockBag = mock(Bag.class);
        Predicate<String> mockPredicate = mock(Predicate.class);
        when(mockPredicate.evaluate("valid")).thenReturn(true);
        PredicatedBag<String> predicatedBag = new PredicatedBag<>(mockBag, mockPredicate);

        // When
        boolean result = predicatedBag.add("valid", 1);

        // Then
        assertTrue(result);
        verify(mockBag).add("valid", 1);
    }

    @Test
    public void testAddInvalidObject() {
        // Given
        Bag<String> mockBag = mock(Bag.class);
        Predicate<String> mockPredicate = mock(Predicate.class);
        when(mockPredicate.evaluate("invalid")).thenReturn(false);
        PredicatedBag<String> predicatedBag = new PredicatedBag<>(mockBag, mockPredicate);

        // When
        boolean result = predicatedBag.add("invalid", 1);

        // Then
        assertFalse(result);
        verify(mockBag, never()).add("invalid", 1);
    }
}
