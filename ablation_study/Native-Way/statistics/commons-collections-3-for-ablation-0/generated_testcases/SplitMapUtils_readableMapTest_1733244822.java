
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.Test;

public class SplitMapUtils_readableMapTest {

    @Test
    public void testReadableMap_GetIsMapAndIterableMap() {
        IterableMap<String, String> mockMap = mock(IterableMap.class);
        IterableMap<String, String> result = SplitMapUtils.readableMap(mockMap);
        assertEquals(mockMap, result);
    }

    @Test
    public void testReadableMap_GetIsMapButNotIterableMap() {
        Map<String, String> mockMap = mock(Map.class);
        when(mockMap.entrySet()).thenReturn(new HashedMap<String, String>().entrySet());
        IterableMap<String, String> result = SplitMapUtils.readableMap((Get<String, String>) mockMap);
        assertEquals(MapUtils.iterableMap(mockMap), result);
    }

    @Test
    public void testReadableMap_GetIsNotMap() {
        Get<String, String> mockGet = mock(Get.class);
        IterableMap<String, String> result = SplitMapUtils.readableMap(mockGet);
        assertEquals(WrappedGet.class, result.getClass());
    }

    @Test
    public void testReadableMap_NullGet() {
        assertThrows(NullPointerException.class, () -> {
            SplitMapUtils.readableMap(null);
        });
    }
}
