
package net.datafaker.internal.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CopyOnWriteMap_updateNestedValueTest {

    private CopyOnWriteMap<String, Map<String, Integer>> map;

    @BeforeEach
    public void setUp() {
        map = new CopyOnWriteMap<>(HashMap::new);
    }

    @Test
    public void testUpdateNestedValue_KeyExists() {
        // Given
        Map<String, Integer> nestedMap = new HashMap<>();
        nestedMap.put("key2", 1);
        map.put("key1", nestedMap);

        // When
        map.updateNestedValue("key1", HashMap::new, "key2", 2);

        // Then
        assertEquals(2, map.get("key1").get("key2"));
    }

    @Test
    public void testUpdateNestedValue_KeyDoesNotExist() {
        // Given
        Supplier<Map<String, Integer>> nestedMapSupplier = HashMap::new;

        // When
        map.updateNestedValue("key1", nestedMapSupplier, "key2", 1);

        // Then
        assertTrue(map.containsKey("key1"));
        assertEquals(1, map.get("key1").get("key2"));
    }
}
