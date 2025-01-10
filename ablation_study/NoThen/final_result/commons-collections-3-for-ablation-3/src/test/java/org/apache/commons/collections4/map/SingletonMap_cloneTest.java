
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SingletonMap_cloneTest {

    @Test
    void testClone() {
        SingletonMap<String, String> original = new SingletonMap<>("key", "value");
        SingletonMap<String, String> cloned = original.clone();

        assertNotSame(original, cloned);
        assertEquals(original.getKey(), cloned.getKey());
        assertEquals(original.getValue(), cloned.getValue());
    }
}
