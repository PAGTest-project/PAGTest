
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_isEmptyTest {

    private CompositeMap<String, String> compositeMap;

    @BeforeEach
    public void setUp() {
        compositeMap = new CompositeMap<>();
    }

    @Test
    public void testIsEmpty_AllMapsEmpty() {
        // Given: A CompositeMap with no maps added
        // When: isEmpty is called
        boolean result = compositeMap.isEmpty();
        // Then: The result should be true
        assertTrue(result);
    }

    @Test
    public void testIsEmpty_OneMapNotEmpty() {
        // Given: A CompositeMap with one non-empty map added
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        compositeMap.addComposited(map1);
        // When: isEmpty is called
        boolean result = compositeMap.isEmpty();
        // Then: The result should be false
        assertFalse(result);
    }

    @Test
    public void testIsEmpty_AllMapsCleared() {
        // Given: A CompositeMap with one non-empty map added
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        compositeMap.addComposited(map1);
        // When: The map is cleared
        compositeMap.clear();
        // Then: isEmpty should return true
        assertTrue(compositeMap.isEmpty());
    }

    @Test
    public void testIsEmpty_MultipleMapsWithOneNotEmpty() {
        // Given: A CompositeMap with multiple maps, one of which is not empty
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        map2.put("key2", "value2");
        compositeMap.addComposited(map1);
        compositeMap.addComposited(map2);
        // When: isEmpty is called
        boolean result = compositeMap.isEmpty();
        // Then: The result should be false
        assertFalse(result);
    }
}
