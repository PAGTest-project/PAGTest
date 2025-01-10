
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StaticBucketMap_clearTest {

    @Test
    void testClear() {
        // Given
        StaticBucketMap<Integer, String> map = new StaticBucketMap<>(1);
        map.put(1, "one");
        map.put(2, "two");

        // When
        map.clear();

        // Then
        assertTrue(map.isEmpty());
    }
}
