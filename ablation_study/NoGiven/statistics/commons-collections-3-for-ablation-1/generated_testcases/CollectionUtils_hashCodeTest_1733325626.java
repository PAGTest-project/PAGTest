
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;

class CollectionUtils_hashCodeTest {

    @Test
    void testHashCodeWithNonNullCollectionAndEquator() {
        Collection<String> collection = Arrays.asList("a", "b", "c");
        Equator<String> equator = mock(Equator.class);
        when(equator.hash("a")).thenReturn(1);
        when(equator.hash("b")).thenReturn(2);
        when(equator.hash("c")).thenReturn(3);

        int result = CollectionUtils.hashCode(collection, equator);

        assertEquals(96354, result);
    }

    @Test
    void testHashCodeWithNullCollection() {
        Equator<String> equator = mock(Equator.class);

        int result = CollectionUtils.hashCode(null, equator);

        assertEquals(0, result);
    }

    @Test
    void testHashCodeWithNullEquator() {
        Collection<String> collection = Arrays.asList("a", "b", "c");

        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.hashCode(collection, null);
        });
    }
}
