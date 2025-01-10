
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Collections; // Added import

import static org.junit.jupiter.api.Assertions.*;

class MapUtils_toMapTest {

    @Test
    void testToMap() {
        // Given
        ResourceBundle resourceBundle = new ResourceBundle() {
            private final Map<String, Object> map = new HashMap<String, Object>() {{
                put("key1", "value1");
                put("key2", "value2");
            }};

            @Override
            protected Object handleGetObject(String key) {
                return map.get(key);
            }

            @Override
            public Enumeration<String> getKeys() {
                return Collections.enumeration(map.keySet());
            }
        };

        // When
        Map<String, Object> result = MapUtils.toMap(resourceBundle);

        // Then
        assertEquals(2, result.size());
        assertEquals("value1", result.get("key1"));
        assertEquals("value2", result.get("key2"));
    }

    @Test
    void testToMap_NullResourceBundle() {
        // Given
        ResourceBundle resourceBundle = null;

        // When & Then
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            MapUtils.toMap(resourceBundle);
        });
        assertEquals("resourceBundle", exception.getMessage());
    }
}
