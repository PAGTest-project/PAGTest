
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.collection.CompositeCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_valuesTest {

    private CompositeMap<String, String> compositeMap;

    @BeforeEach
    public void setUp() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        Map<String, String> map2 = new HashMap<>();
        map2.put("key3", "value3");
        map2.put("key4", "value4");

        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testValues() {
        Collection<String> values = compositeMap.values();
        assertTrue(values instanceof CompositeCollection);
        assertEquals(4, values.size());
        assertTrue(values.contains("value1"));
        assertTrue(values.contains("value2"));
        assertTrue(values.contains("value3"));
        assertTrue(values.contains("value4"));
    }

    @Test
    public void testValuesAfterAddComposited() {
        Map<String, String> map3 = new HashMap<>();
        map3.put("key5", "value5");
        map3.put("key6", "value6");

        compositeMap.addComposited(map3);

        Collection<String> values = compositeMap.values();
        assertTrue(values instanceof CompositeCollection);
        assertEquals(6, values.size());
        assertTrue(values.contains("value1"));
        assertTrue(values.contains("value2"));
        assertTrue(values.contains("value3"));
        assertTrue(values.contains("value4"));
        assertTrue(values.contains("value5"));
        assertTrue(values.contains("value6"));
    }

    @Test
    public void testValuesAfterRemoveComposited() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        compositeMap.removeComposited(map1);

        Collection<String> values = compositeMap.values();
        assertTrue(values instanceof CompositeCollection);
        assertEquals(2, values.size());
        assertFalse(values.contains("value1"));
        assertFalse(values.contains("value2"));
        assertTrue(values.contains("value3"));
        assertTrue(values.contains("value4"));
    }

    @Test
    public void testValuesWithEmptyCompositeMap() {
        compositeMap = new CompositeMap<>();
        Collection<String> values = compositeMap.values();
        assertTrue(values instanceof CompositeCollection);
        assertTrue(values.isEmpty());
    }

    @Test
    public void testValuesWithNullCompositeMap() {
        assertThrows(IllegalArgumentException.class, () -> {
            compositeMap.addComposited(null);
        });
    }
}
