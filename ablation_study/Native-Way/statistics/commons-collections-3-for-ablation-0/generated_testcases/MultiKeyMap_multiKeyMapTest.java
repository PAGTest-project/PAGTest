
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.commons.collections4.map.AbstractHashedMap;
import org.apache.commons.collections4.keyvalue.MultiKey;

public class MultiKeyMap_multiKeyMapTest {

    private static final Integer I1 = 1;
    private static final Integer I2 = 2;

    private AbstractHashedMap<MultiKey<? extends Integer>, String> hashedMap;

    @BeforeEach
    public void setUp() {
        hashedMap = new AbstractHashedMap<MultiKey<? extends Integer>, String>() {
            // Dummy implementation for testing purposes
        };
    }

    @Test
    public void testMultiKeyMapWithEmptyMap() {
        MultiKeyMap<Integer, String> result = MultiKeyMap.multiKeyMap(hashedMap);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testMultiKeyMapWithNonEmptyMap() {
        hashedMap = new AbstractHashedMap<MultiKey<? extends Integer>, String>() {
            // Dummy implementation for testing purposes
        };
        hashedMap.put(new MultiKey<>(I1, I2), "1-2");
        assertThrows(IllegalArgumentException.class, () -> {
            MultiKeyMap.multiKeyMap(hashedMap);
        });
    }

    @Test
    public void testMultiKeyMapWithNullMap() {
        assertThrows(NullPointerException.class, () -> {
            MultiKeyMap.multiKeyMap(null);
        });
    }
}
