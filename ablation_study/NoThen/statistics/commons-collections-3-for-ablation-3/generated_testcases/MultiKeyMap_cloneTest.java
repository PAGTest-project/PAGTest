
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MultiKeyMap_cloneTest {

    @Test
    void testClone() {
        MultiKeyMap<String, String> originalMap = new MultiKeyMap<>();
        originalMap.put("key1", "key2", "value1");

        MultiKeyMap<String, String> clonedMap = originalMap.clone();

        assertEquals(originalMap.get("key1", "key2"), clonedMap.get("key1", "key2"));
    }
}
