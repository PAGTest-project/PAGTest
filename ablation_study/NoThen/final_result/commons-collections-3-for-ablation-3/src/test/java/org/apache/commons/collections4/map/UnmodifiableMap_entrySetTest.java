
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableMap_entrySetTest {

    @Test
    public void testEntrySet() {
        // Given
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");
        Map<String, String> unmodifiableMap = UnmodifiableMap.unmodifiableMap(originalMap);

        // When
        Set<Map.Entry<String, String>> entrySet = unmodifiableMap.entrySet();

        // Then
        assertEquals(2, entrySet.size());
        assertTrue(entrySet.containsAll(originalMap.entrySet()));
    }
}
