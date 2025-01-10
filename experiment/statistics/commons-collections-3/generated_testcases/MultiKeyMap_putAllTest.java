
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MultiKeyMap_putAllTest {

    @Test
    public void testPutAll() {
        MultiKeyMap<String, String> multiKeyMap = new MultiKeyMap<>();
        Map<MultiKey<String>, String> mapToCopy = new HashMap<>();
        mapToCopy.put(new MultiKey<>("key1", "key2"), "value1");
        mapToCopy.put(new MultiKey<>("key3", "key4"), "value2");

        assertDoesNotThrow(() -> multiKeyMap.putAll(mapToCopy));
    }
}
