
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListOrderedMap_clearTest {

    @Test
    void testClear() {
        // Given
        ListOrderedMap<String, String> map = new ListOrderedMap<>(new HashMap<>());
        map.put("key1", "value1");
        map.put("key2", "value2");

        // When
        map.clear();

        // Then
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }
}
