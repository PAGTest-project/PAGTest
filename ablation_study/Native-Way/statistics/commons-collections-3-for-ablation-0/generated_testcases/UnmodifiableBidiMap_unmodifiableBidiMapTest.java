
package org.apache.commons.collections4.bidimap;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class UnmodifiableBidiMap_unmodifiableBidiMapTest {

    @Test
    public void testUnmodifiableBidiMapWithUnmodifiableMap() {
        BidiMap<String, Integer> unmodifiableMap = new UnmodifiableBidiMap<>(new DualHashBidiMap<>());
        BidiMap<String, Integer> result = UnmodifiableBidiMap.unmodifiableBidiMap(unmodifiableMap);
        assertSame(unmodifiableMap, result);
    }

    @Test
    public void testUnmodifiableBidiMapWithModifiableMap() {
        BidiMap<String, Integer> modifiableMap = new DualHashBidiMap<>();
        BidiMap<String, Integer> result = UnmodifiableBidiMap.unmodifiableBidiMap(modifiableMap);
        assertInstanceOf(UnmodifiableBidiMap.class, result);
    }
}
