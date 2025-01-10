
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableBidiMap_entrySetTest {

    @Test
    public void testEntrySet() {
        BidiMap<String, Integer> originalMap = new DualHashBidiMap<>();
        originalMap.put("one", 1);
        originalMap.put("two", 2);

        BidiMap<String, Integer> unmodifiableBidiMap = new UnmodifiableBidiMap<>(originalMap);
        Set<Map.Entry<String, Integer>> entrySet = unmodifiableBidiMap.entrySet();

        assertTrue(entrySet instanceof UnmodifiableEntrySet);
    }
}
