
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableBag_unmodifiableBagTest {

    @Test
    void testUnmodifiableBagWithUnmodifiableBag() {
        Bag<String> mockBag = mock(Bag.class);
        when(mockBag.isUnmodifiable()).thenReturn(true);

        Bag<String> result = UnmodifiableBag.unmodifiableBag(mockBag);

        assertSame(mockBag, result);
    }

    @Test
    void testUnmodifiableBagWithModifiableBag() {
        Bag<String> mockBag = mock(Bag.class);
        when(mockBag.isUnmodifiable()).thenReturn(false);

        Bag<String> result = UnmodifiableBag.unmodifiableBag(mockBag);

        assertTrue(result instanceof UnmodifiableBag);
        assertNotSame(mockBag, result);
    }
}
