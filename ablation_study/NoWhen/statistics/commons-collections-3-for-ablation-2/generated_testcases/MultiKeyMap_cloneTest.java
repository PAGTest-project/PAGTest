
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MultiKeyMap_cloneTest {

    @Test
    void testClone() {
        MultiKeyMap<String, String> original = new MultiKeyMap<>();
        original.put("key1", "key2", "value");

        MultiKeyMap<String, String> cloned = original.clone();

        assertEquals(original.get("key1", "key2"), cloned.get("key1", "key2"));
    }
}
