
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_hashCodeTest {

    private Map<String, String> one;
    private Map<String, String> two;

    @BeforeEach
    public void setUp() {
        one = new HashMap<>();
        one.put("1", "one");
        one.put("2", "two");

        two = new HashMap<>();
        two.put("3", "three");
        two.put("4", "four");
    }

    @Test
    public void testHashCodeWithEmptyCompositeMap() {
        CompositeMap<String, String> map = new CompositeMap<>();
        assertEquals(0, map.hashCode());
    }

    @Test
    public void testHashCodeWithNonEmptyCompositeMap() {
        CompositeMap<String, String> map = new CompositeMap<>(one, two);
        int expectedHashCode = one.hashCode() + two.hashCode();
        assertEquals(expectedHashCode, map.hashCode());
    }

    @Test
    public void testHashCodeWithSingleMap() {
        CompositeMap<String, String> map = new CompositeMap<>(one);
        assertEquals(one.hashCode(), map.hashCode());
    }

    @Test
    public void testHashCodeWithDuplicateKeys() {
        Map<String, String> duplicateKeyMap = new HashMap<>();
        duplicateKeyMap.put("1", "duplicateOne");
        CompositeMap<String, String> map = new CompositeMap<>(one, duplicateKeyMap);
        int expectedHashCode = one.hashCode() + duplicateKeyMap.hashCode();
        assertEquals(expectedHashCode, map.hashCode());
    }

    @Test
    public void testHashCodeWithNullValues() {
        one.put("5", null);
        CompositeMap<String, String> map = new CompositeMap<>(one, two);
        int expectedHashCode = one.hashCode() + two.hashCode();
        assertEquals(expectedHashCode, map.hashCode());
    }
}
