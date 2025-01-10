
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StaticBucketMap_hashCodeTest {

    @Test
    void testHashCode() {
        StaticBucketMap<Integer, String> map = new StaticBucketMap<>(1);
        map.put(1, "one");
        map.put(2, "two");

        int expectedHashCode = 1.hashCode() + 2.hashCode();
        assertEquals(expectedHashCode, map.hashCode());
    }
}
