
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.set.UnmodifiableSet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableMap_keySetTest {

    @Test
    public void testKeySet() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        Map<String, String> unmodifiableMap = UnmodifiableMap.unmodifiableMap(map);
        Set<String> keySet = unmodifiableMap.keySet();

        assertTrue(keySet instanceof UnmodifiableSet);
        assertTrue(keySet.contains("key1"));
        assertTrue(keySet.contains("key2"));
    }
}
