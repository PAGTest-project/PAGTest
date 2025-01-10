
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.set.UnmodifiableEntrySet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableMap_entrySetTest {

    @Test
    public void testEntrySet() {
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");

        UnmodifiableMap<String, String> unmodifiableMap = new UnmodifiableMap<>(originalMap);
        Set<Map.Entry<String, String>> entrySet = unmodifiableMap.entrySet();

        assertTrue(entrySet instanceof UnmodifiableEntrySet);
    }
}
