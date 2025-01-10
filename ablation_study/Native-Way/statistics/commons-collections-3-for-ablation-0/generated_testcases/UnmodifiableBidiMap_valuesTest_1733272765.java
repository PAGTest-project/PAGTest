
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableBidiMap_valuesTest {

    @Test
    public void testValues() {
        BidiMap<String, Integer> originalMap = new DualHashBidiMap<>();
        originalMap.put("one", 1);
        originalMap.put("two", 2);

        UnmodifiableBidiMap<String, Integer> unmodifiableBidiMap = new UnmodifiableBidiMap<>(originalMap);
        Set<Integer> values = unmodifiableBidiMap.values();

        assertTrue(values instanceof UnmodifiableSet);
        assertEquals(2, values.size());
        assertTrue(values.contains(1));
        assertTrue(values.contains(2));
    }
}
