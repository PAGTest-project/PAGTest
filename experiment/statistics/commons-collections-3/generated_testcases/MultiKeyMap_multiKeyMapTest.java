
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.apache.commons.collections4.keyvalue.MultiKey;

public class MultiKeyMap_multiKeyMapTest {

    @Test
    public void testMultiKeyMapWithEmptyMap() {
        AbstractHashedMap<MultiKey<? extends String>, String> map = new HashedMap<>();
        MultiKeyMap<String, String> multiKeyMap = MultiKeyMap.multiKeyMap(map);
        assertNotNull(multiKeyMap);
        assertTrue(multiKeyMap.isEmpty());
    }

    @Test
    public void testMultiKeyMapWithNonEmptyMap() {
        AbstractHashedMap<MultiKey<? extends String>, String> map = new HashedMap<>();
        map.put(new MultiKey<>("key1", "key2"), "value");
        assertThrows(IllegalArgumentException.class, () -> MultiKeyMap.multiKeyMap(map));
    }

    @Test
    public void testMultiKeyMapWithNullMap() {
        assertThrows(NullPointerException.class, () -> MultiKeyMap.multiKeyMap(null));
    }
}
