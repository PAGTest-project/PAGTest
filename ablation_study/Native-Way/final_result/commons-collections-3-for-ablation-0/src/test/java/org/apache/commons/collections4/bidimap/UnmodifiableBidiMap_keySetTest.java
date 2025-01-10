
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableBidiMap_keySetTest {

    @Test
    public void testKeySet() {
        // Given
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        BidiMap<String, String> bidiMap = UnmodifiableBidiMap.unmodifiableBidiMap(new DualHashBidiMap<>(map));

        // When
        Set<String> keySet = bidiMap.keySet();

        // Then
        assertTrue(keySet instanceof UnmodifiableSet);
        assertEquals(2, keySet.size());
        assertTrue(keySet.contains("key1"));
        assertTrue(keySet.contains("key2"));
    }
}
