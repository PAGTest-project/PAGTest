
package org.apache.commons.collections4.multiset;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PredicatedMultiSet_addTest {

    @Test
    public void testAddValidObject() {
        MultiSet<String> mockMultiSet = mock(MultiSet.class);
        Predicate<String> mockPredicate = mock(Predicate.class);
        PredicatedMultiSet<String> predicatedMultiSet = new PredicatedMultiSet<>(mockMultiSet, mockPredicate);

        String validObject = "valid";
        when(mockPredicate.evaluate(validObject)).thenReturn(true);
        when(mockMultiSet.add(validObject, 1)).thenReturn(1);

        int result = predicatedMultiSet.add(validObject, 1);

        assertEquals(1, result);
        verify(mockPredicate).evaluate(validObject);
        verify(mockMultiSet).add(validObject, 1);
    }

    @Test
    public void testAddInvalidObject() {
        MultiSet<String> mockMultiSet = mock(MultiSet.class);
        Predicate<String> mockPredicate = mock(Predicate.class);
        PredicatedMultiSet<String> predicatedMultiSet = new PredicatedMultiSet<>(mockMultiSet, mockPredicate);

        String invalidObject = "invalid";
        when(mockPredicate.evaluate(invalidObject)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            predicatedMultiSet.add(invalidObject, 1);
        });

        verify(mockPredicate).evaluate(invalidObject);
        verify(mockMultiSet, never()).add(any(), anyInt());
    }
}
