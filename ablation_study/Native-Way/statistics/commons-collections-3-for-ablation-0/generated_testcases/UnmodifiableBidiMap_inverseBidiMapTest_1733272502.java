
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.BidiMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableBidiMap_inverseBidiMapTest {

    @Test
    void testInverseBidiMap() {
        // Given
        BidiMap<String, Integer> originalMap = mock(BidiMap.class);
        BidiMap<Integer, String> inverseMap = mock(BidiMap.class);
        when(originalMap.inverseBidiMap()).thenReturn(inverseMap);

        UnmodifiableBidiMap<String, Integer> unmodifiableBidiMap = new UnmodifiableBidiMap<>(originalMap);

        // When
        BidiMap<Integer, String> result1 = unmodifiableBidiMap.inverseBidiMap();
        BidiMap<Integer, String> result2 = unmodifiableBidiMap.inverseBidiMap();

        // Then
        assertNotNull(result1);
        assertSame(result1, result2);
        verify(originalMap, times(1)).inverseBidiMap();
    }
}
