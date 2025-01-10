
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableSortedBag_unmodifiableSortedBagTest {

    @Test
    void testUnmodifiableSortedBag_WithUnmodifiableBag() {
        SortedBag<String> mockBag = mock(SortedBag.class);
        when(mockBag instanceof Unmodifiable).thenReturn(true);

        SortedBag<String> result = UnmodifiableSortedBag.unmodifiableSortedBag(mockBag);

        assertSame(mockBag, result);
    }

    @Test
    void testUnmodifiableSortedBag_WithModifiableBag() {
        SortedBag<String> mockBag = mock(SortedBag.class);
        when(mockBag instanceof Unmodifiable).thenReturn(false);

        SortedBag<String> result = UnmodifiableSortedBag.unmodifiableSortedBag(mockBag);

        assertTrue(result instanceof UnmodifiableSortedBag);
        assertNotSame(mockBag, result);
    }
}
