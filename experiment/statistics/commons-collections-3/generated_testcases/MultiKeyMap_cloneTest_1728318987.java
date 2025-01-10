
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.collections4.keyvalue.MultiKey;

class MultiKeyMap_cloneTest {

    @Test
    void testClone() {
        MultiKeyMap<String, String> original = new MultiKeyMap<>();
        original.put(new MultiKey<>("key1", "key2"), "value");

        MultiKeyMap<String, String> cloned = original.clone();

        assertNotNull(cloned);
        assertEquals(original.get(new MultiKey<>("key1", "key2")), cloned.get(new MultiKey<>("key1", "key2")));
    }

    @Test
    void testCloneNotSupportedException() {
        MultiKeyMap<String, String> original = new MultiKeyMap<String, String>() {
            @Override
            protected Object clone() throws CloneNotSupportedException {
                throw new CloneNotSupportedException();
            }
        };

        assertThrows(UnsupportedOperationException.class, original::clone);
    }
}
