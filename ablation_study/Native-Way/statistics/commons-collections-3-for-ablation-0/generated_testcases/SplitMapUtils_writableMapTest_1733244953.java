
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class SplitMapUtils_writableMapTest {

    @Test
    public void testWritableMapWithMap() {
        Map<String, String> mockMap = mock(Map.class);
        when(mockMap.put("key", "value")).thenReturn("value");

        Map<String, String> result = SplitMapUtils.writableMap((Put<String, String>) mockMap);
        assertEquals("value", result.put("key", "value"));
    }

    @Test
    public void testWritableMapWithNonMap() {
        Put<String, String> mockPut = mock(Put.class);
        when(mockPut.put("key", "value")).thenReturn("value");

        Map<String, String> result = SplitMapUtils.writableMap(mockPut);
        assertEquals("value", result.put("key", "value"));
    }

    @Test
    public void testWritableMapWithNull() {
        assertThrows(NullPointerException.class, () -> {
            SplitMapUtils.writableMap(null);
        });
    }
}
