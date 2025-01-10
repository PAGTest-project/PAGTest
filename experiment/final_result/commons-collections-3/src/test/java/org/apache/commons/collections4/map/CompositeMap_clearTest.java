
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_clearTest {

    private CompositeMap<String, String> compositeMap;
    private Map<String, String> map1;
    private Map<String, String> map2;

    @BeforeEach
    public void setUp() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testClear() {
        // Given
        map1.put("key1", "value1");
        map2.put("key2", "value2");

        // When
        compositeMap.clear();

        // Then
        assertTrue(map1.isEmpty());
        assertTrue(map2.isEmpty());
        assertTrue(compositeMap.isEmpty());
    }
}
