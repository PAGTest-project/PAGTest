
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.collection.CompositeCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_valuesTest {

    private CompositeMap<String, String> compositeMap;
    private Map<String, String> map1;
    private Map<String, String> map2;

    @BeforeEach
    public void setUp() {
        map1 = new HashMap<>();
        map1.put("1", "one");
        map1.put("2", "two");

        map2 = new HashMap<>();
        map2.put("3", "three");
        map2.put("4", "four");

        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testValues() {
        Collection<String> values = compositeMap.values();
        assertTrue(values instanceof CompositeCollection);
        assertEquals(4, values.size());
        assertTrue(values.contains("one"));
        assertTrue(values.contains("two"));
        assertTrue(values.contains("three"));
        assertTrue(values.contains("four"));
    }

    @Test
    public void testValuesAfterAddComposited() {
        Map<String, String> map3 = new HashMap<>();
        map3.put("5", "five");
        compositeMap.addComposited(map3);

        Collection<String> values = compositeMap.values();
        assertTrue(values instanceof CompositeCollection);
        assertEquals(5, values.size());
        assertTrue(values.contains("one"));
        assertTrue(values.contains("two"));
        assertTrue(values.contains("three"));
        assertTrue(values.contains("four"));
        assertTrue(values.contains("five"));
    }

    @Test
    public void testValuesAfterRemoveComposited() {
        compositeMap.removeComposited(map2);

        Collection<String> values = compositeMap.values();
        assertTrue(values instanceof CompositeCollection);
        assertEquals(2, values.size());
        assertTrue(values.contains("one"));
        assertTrue(values.contains("two"));
        assertFalse(values.contains("three"));
        assertFalse(values.contains("four"));
    }
}
