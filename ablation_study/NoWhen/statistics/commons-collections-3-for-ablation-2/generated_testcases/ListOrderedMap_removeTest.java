
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class ListOrderedMap_removeTest {

    private ListOrderedMap<String, String> map;

    @BeforeEach
    void setUp() {
        map = new ListOrderedMap<>(new HashMap<>());
        map.put("key1", "value1");
        map.put("key2", "value2");
    }

    @Test
    void testRemoveExistingKey() {
        // Given
        assertTrue(map.containsKey("key1"));

        // When
        String result = map.remove("key1");

        // Then
        assertEquals("value1", result);
        assertFalse(map.containsKey("key1"));
    }

    @Test
    void testRemoveNonExistingKey() {
        // Given
        assertFalse(map.containsKey("key3"));

        // When
        String result = map.remove("key3");

        // Then
        assertNull(result);
        assertFalse(map.containsKey("key3"));
    }
}
