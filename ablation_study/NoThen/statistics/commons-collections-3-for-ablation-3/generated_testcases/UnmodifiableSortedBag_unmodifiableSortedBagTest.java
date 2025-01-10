
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableSortedBag_unmodifiableSortedBagTest {

    @Test
    void testUnmodifiableSortedBag_ReturnsSameInstanceIfAlreadyUnmodifiable() {
        // Given
        SortedBag<String> mockBag = mock(SortedBag.class);
        when(mockBag.isUnmodifiable()).thenReturn(true);

        // When
        SortedBag<String> result = UnmodifiableSortedBag.unmodifiableSortedBag(mockBag);

        // Then
        assertSame(mockBag, result);
    }

    @Test
    void testUnmodifiableSortedBag_ReturnsNewUnmodifiableSortedBagIfNotAlreadyUnmodifiable() {
        // Given
        SortedBag<String> mockBag = mock(SortedBag.class);
        when(mockBag.isUnmodifiable()).thenReturn(false);

        // When
        SortedBag<String> result = UnmodifiableSortedBag.unmodifiableSortedBag(mockBag);

        // Then
        assertTrue(result instanceof UnmodifiableSortedBag);
        assertNotSame(mockBag, result);
    }
}
