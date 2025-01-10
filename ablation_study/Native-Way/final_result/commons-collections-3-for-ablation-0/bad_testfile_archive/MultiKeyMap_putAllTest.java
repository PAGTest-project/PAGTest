
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiKeyMap_putAllTest {

    private MultiKeyMap<String, String> multiKeyMap;

    @BeforeEach
    public void setUp() {
        multiKeyMap = new MultiKeyMap<>();
    }

    @Test
    public void testPutAll() {
        Map<MultiKey<? extends String>, String> mapToCopy = new HashMap<>();
        mapToCopy.put(new MultiKey<>("key1", "key2"), "value1");
        mapToCopy.put(new MultiKey<>("key3", "key4"), "value2");

        multiKeyMap.putAll(mapToCopy);

        assertEquals(2, multiKeyMap.size());
        assertEquals("value1", multiKeyMap.get("key1", "key2"));
        assertEquals("value2", multiKeyMap.get("key3", "key4"));
    }

    @Test
    public void testPutAllWithNullKey() {
        Map<MultiKey<? extends String>, String> mapToCopy = new HashMap<>();
        mapToCopy.put(null, "value1");

        assertThrows(NullPointerException.class, () -> {
            multiKeyMap.putAll(mapToCopy);
        });
    }

    @Test
    public void testPutAllWithInvalidKey() {
        Map<MultiKey<? extends String>, String> mapToCopy = new HashMap<>();
        mapToCopy.put(new MultiKey<>("key1"), "value1");

        assertThrows(IllegalArgumentException.class, () -> {
            multiKeyMap.putAll(mapToCopy);
        });
    }
}
