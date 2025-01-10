
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StaticBucketMap_sizeTest {

    @Test
    void testSize() {
        StaticBucketMap<Integer, String> map = new StaticBucketMap<>(1);

        // Given: Map with one entry
        map.put(1, "one");

        // When: Calculating size
        int size = map.size();

        // Then: Size should be 1
        assertEquals(1, size);

        // Given: Map with one entry removed
        map.remove(1);

        // When: Calculating size
        size = map.size();

        // Then: Size should be 0
        assertEquals(0, size);
    }
}
