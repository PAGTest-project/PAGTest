
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaticBucketMap_clearTest {
    private StaticBucketMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new StaticBucketMap<>(17);
    }

    @Test
    public void testClear() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.clear();

        for (int i = 0; i < map.buckets.length; i++) {
            assertNull(map.buckets[i]);
            assertEquals(0, map.locks[i].size);
        }
    }
}
