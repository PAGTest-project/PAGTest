
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnmodifiableSortedBidiMap_unmodifiableSortedBidiMapTest {

    @Test
    public void testUnmodifiableSortedBidiMapWithUnmodifiableMap() {
        SortedBidiMap<String, String> originalMap = new TreeBidiMap<>();
        SortedBidiMap<String, String> unmodifiableMap = UnmodifiableSortedBidiMap.unmodifiableSortedBidiMap(originalMap);
        assertTrue(unmodifiableMap instanceof Unmodifiable);

        SortedBidiMap<String, String> result = UnmodifiableSortedBidiMap.unmodifiableSortedBidiMap(unmodifiableMap);
        assertSame(unmodifiableMap, result);
    }

    @Test
    public void testUnmodifiableSortedBidiMapWithModifiableMap() {
        SortedBidiMap<String, String> originalMap = new TreeBidiMap<>();
        SortedBidiMap<String, String> result = UnmodifiableSortedBidiMap.unmodifiableSortedBidiMap(originalMap);
        assertTrue(result instanceof UnmodifiableSortedBidiMap);
        assertNotSame(originalMap, result);
    }

    @Test
    public void testUnmodifiableSortedBidiMapWithNullMap() {
        assertThrows(NullPointerException.class, () -> {
            UnmodifiableSortedBidiMap.unmodifiableSortedBidiMap(null);
        });
    }
}
