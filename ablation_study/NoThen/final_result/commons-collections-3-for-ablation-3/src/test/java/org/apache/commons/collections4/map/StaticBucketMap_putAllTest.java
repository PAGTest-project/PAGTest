
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaticBucketMap_putAllTest {

    @Test
    public void testPutAll() {
        // Given
        StaticBucketMap<Integer, String> map = new StaticBucketMap<>(10);
        Map<Integer, String> inputMap = new HashMap<>();
        inputMap.put(1, "One");
        inputMap.put(2, "Two");
        inputMap.put(3, "Three");

        // When
        map.putAll(inputMap);

        // Then
        assertEquals(3, map.size());
    }
}
