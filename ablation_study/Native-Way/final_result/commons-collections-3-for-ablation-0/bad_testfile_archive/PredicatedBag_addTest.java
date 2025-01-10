
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PredicatedBag_addTest {

    @Test
    void testAddValidObject() {
        // Given
        Bag<String> mockBag = mock(Bag.class);
        Predicate<String> mockPredicate = mock(Predicate.class);
        PredicatedBag<String> predicatedBag = new PredicatedBag<>(mockBag, mockPredicate);
        String validObject = "valid";
        when(mockPredicate.evaluate(validObject)).thenReturn(true);
        when(mockBag.add(validObject, 1)).thenReturn(true);

        // When
        boolean result = predicatedBag.add(validObject, 1);

        // Then
        assertTrue(result);
        verify(mockPredicate).evaluate(validObject);
        verify(mockBag).add(validObject, 1);
    }

    @Test
    void testAddInvalidObject() {
        // Given
        Bag<String> mockBag = mock(Bag.class);
        Predicate<String> mockPredicate = mock(Predicate.class);
        PredicatedBag<String> predicatedBag = new PredicatedBag<>(mockBag, mockPredicate);
        String invalidObject = "invalid";
        when(mockPredicate.evaluate(invalidObject)).thenReturn(false);

        // When
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            predicatedBag.add(invalidObject, 1);
        });

        // Then
        assertEquals("Predicate rejected object", exception.getMessage());
        verify(mockPredicate).evaluate(invalidObject);
        verify(mockBag, never()).add(any(), anyInt());
    }
}
