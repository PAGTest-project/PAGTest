
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_removeTest {

    private CompositeMap<String, String> compositeMap;
    private Map<String, String> map1;
    private Map<String, String> map2;

    @BeforeEach
    public void setUp() {
        map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        map2 = new HashMap<>();
        map2.put("key3", "value3");
        map2.put("key4", "value4");

        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testRemoveExistingKey() {
        // Given
        assertTrue(compositeMap.containsKey("key1"));

        // When
        String removedValue = compositeMap.remove("key1");

        // Then
        assertEquals("value1", removedValue);
        assertFalse(compositeMap.containsKey("key1"));
    }

    @Test
    public void testRemoveNonExistingKey() {
        // Given
        assertFalse(compositeMap.containsKey("nonExistingKey"));

        // When
        String removedValue = compositeMap.remove("nonExistingKey");

        // Then
        assertNull(removedValue);
    }

    @Test
    public void testRemoveFromSecondMap() {
        // Given
        assertTrue(compositeMap.containsKey("key3"));

        // When
        String removedValue = compositeMap.remove("key3");

        // Then
        assertEquals("value3", removedValue);
        assertFalse(compositeMap.containsKey("key3"));
    }
}
