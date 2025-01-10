
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapUtils_getObjectTest {

    @Test
    public void testGetObject() {
        // Given
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");

        // When
        String result1 = MapUtils.getObject(map, "key1");
        String result2 = MapUtils.getObject(map, "key2");
        String result3 = MapUtils.getObject(null, "key1");

        // Then
        assertEquals("value1", result1);
        assertNull(result2);
        assertNull(result3);
    }
}
