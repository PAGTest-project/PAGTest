
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonMap_hashCodeTest {

    private SingletonMap<String, String> singletonMap;

    @BeforeEach
    public void setUp() {
        singletonMap = new SingletonMap<>("key", "value");
    }

    @Test
    public void testHashCodeWithNonNullKeyAndValue() {
        assertEquals("key".hashCode() ^ "value".hashCode(), singletonMap.hashCode());
    }

    @Test
    public void testHashCodeWithNullKey() {
        singletonMap = new SingletonMap<>(null, "value");
        assertEquals(0 ^ "value".hashCode(), singletonMap.hashCode());
    }

    @Test
    public void testHashCodeWithNullValue() {
        singletonMap = new SingletonMap<>("key", null);
        assertEquals("key".hashCode() ^ 0, singletonMap.hashCode());
    }

    @Test
    public void testHashCodeWithNullKeyAndValue() {
        singletonMap = new SingletonMap<>(null, null);
        assertEquals(0 ^ 0, singletonMap.hashCode());
    }
}
