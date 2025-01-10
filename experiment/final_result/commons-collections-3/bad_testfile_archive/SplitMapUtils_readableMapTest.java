
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;
import java.util.Set;

class SplitMapUtils_readableMapTest {

    @Test
    void testReadableMapWithIterableMap() {
        IterableMap<String, String> mockIterableMap = mock(IterableMap.class);
        IterableMap<String, String> result = SplitMapUtils.readableMap(mockIterableMap);
        assertSame(mockIterableMap, result);
    }

    @Test
    void testReadableMapWithNonIterableMap() {
        Map<String, String> mockMap = mock(Map.class);
        when(mockMap.entrySet()).thenReturn(mock(Set.class));
        IterableMap<String, String> result = SplitMapUtils.readableMap((Get<String, String>) mockMap);
        assertTrue(result instanceof IterableMap);
    }

    @Test
    void testReadableMapWithNonMapGet() {
        Get<String, String> mockGet = mock(Get.class);
        when(mockGet.entrySet()).thenReturn(mock(Set.class));
        IterableMap<String, String> result = SplitMapUtils.readableMap(mockGet);
        assertTrue(result instanceof WrappedGet);
    }

    @Test
    void testReadableMapWithNullGet() {
        assertThrows(NullPointerException.class, () -> {
            SplitMapUtils.readableMap(null);
        });
    }
}
