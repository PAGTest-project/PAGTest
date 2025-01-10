
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.map.AbstractHashedMap;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MultiKeyMap_multiKeyMapTest {

    @Test
    void testMultiKeyMapWithEmptyMap() {
        AbstractHashedMap<MultiKey<? extends String>, String> map = new AbstractHashedMap<>();
        MultiKeyMap<String, String> result = MultiKeyMap.multiKeyMap(map);
        assertNotNull(result);
    }

    @Test
    void testMultiKeyMapWithNonEmptyMap() {
        AbstractHashedMap<MultiKey<? extends String>, String> map = new AbstractHashedMap<>();
        map.put(new MultiKey<>("key1", "key2"), "value");
        assertThrows(IllegalArgumentException.class, () -> MultiKeyMap.multiKeyMap(map));
    }

    @Test
    void testMultiKeyMapWithNullMap() {
        assertThrows(NullPointerException.class, () -> MultiKeyMap.multiKeyMap(null));
    }
}
