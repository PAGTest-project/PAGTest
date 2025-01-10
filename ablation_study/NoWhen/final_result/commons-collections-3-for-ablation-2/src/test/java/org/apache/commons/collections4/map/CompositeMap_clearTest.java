
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CompositeMap_clearTest {

    @Test
    public void testClear() {
        // Given
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        Map<String, String> map2 = new HashMap<>();
        map2.put("key2", "value2");
        CompositeMap<String, String> compositeMap = new CompositeMap<>(map1, map2);

        // When
        compositeMap.clear();

        // Then
        assertTrue(compositeMap.isEmpty());
        assertEquals(0, compositeMap.size());
    }
}
