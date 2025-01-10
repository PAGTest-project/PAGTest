
package org.apache.commons.collections4.multimap;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnmodifiableMultiValuedMap_unmodifiableMultiValuedMapTest {

    @Test
    void testUnmodifiableMultiValuedMapWithUnmodifiableMap() {
        MultiValuedMap<String, String> mockMap = mock(MultiValuedMap.class);
        when(mockMap.getClass().isInstance(Unmodifiable.class)).thenReturn(true);

        UnmodifiableMultiValuedMap<String, String> result = UnmodifiableMultiValuedMap.unmodifiableMultiValuedMap(mockMap);

        assertSame(mockMap, result);
    }

    @Test
    void testUnmodifiableMultiValuedMapWithModifiableMap() {
        MultiValuedMap<String, String> mockMap = mock(MultiValuedMap.class);
        when(mockMap.getClass().isInstance(Unmodifiable.class)).thenReturn(false);

        UnmodifiableMultiValuedMap<String, String> result = UnmodifiableMultiValuedMap.unmodifiableMultiValuedMap(mockMap);

        assertNotNull(result);
        assertNotSame(mockMap, result);
    }
}
