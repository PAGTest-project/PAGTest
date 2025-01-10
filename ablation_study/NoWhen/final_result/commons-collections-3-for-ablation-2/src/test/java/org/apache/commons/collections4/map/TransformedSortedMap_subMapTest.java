
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformedSortedMap_subMapTest {

    private SortedMap<String, String> originalMap;
    private Transformer<String, String> keyTransformer;
    private Transformer<String, String> valueTransformer;

    @BeforeEach
    public void setUp() {
        originalMap = new TreeMap<>();
        originalMap.put("1", "one");
        originalMap.put("2", "two");
        originalMap.put("3", "three");
        originalMap.put("4", "four");
        originalMap.put("5", "five");

        keyTransformer = TransformerUtils.nopTransformer();
        valueTransformer = TransformerUtils.nopTransformer();
    }

    @Test
    public void testSubMap() {
        TransformedSortedMap<String, String> transformedMap = TransformedSortedMap.transformingSortedMap(originalMap, keyTransformer, valueTransformer);

        SortedMap<String, String> subMap = transformedMap.subMap("2", "4");

        assertEquals(2, subMap.size());
        assertTrue(subMap.containsKey("2"));
        assertTrue(subMap.containsKey("3"));
        assertEquals("two", subMap.get("2"));
        assertEquals("three", subMap.get("3"));
    }

    @Test
    public void testSubMapWithNoTransformers() {
        TransformedSortedMap<String, String> transformedMap = TransformedSortedMap.transformingSortedMap(originalMap, null, null);

        SortedMap<String, String> subMap = transformedMap.subMap("2", "4");

        assertEquals(2, subMap.size());
        assertTrue(subMap.containsKey("2"));
        assertTrue(subMap.containsKey("3"));
        assertEquals("two", subMap.get("2"));
        assertEquals("three", subMap.get("3"));
    }

    @Test
    public void testSubMapWithKeyTransformer() {
        keyTransformer = TransformerUtils.stringValueTransformer();
        TransformedSortedMap<String, String> transformedMap = TransformedSortedMap.transformingSortedMap(originalMap, keyTransformer, valueTransformer);

        SortedMap<String, String> subMap = transformedMap.subMap("2", "4");

        assertEquals(2, subMap.size());
        assertTrue(subMap.containsKey("2"));
        assertTrue(subMap.containsKey("3"));
        assertEquals("two", subMap.get("2"));
        assertEquals("three", subMap.get("3"));
    }

    @Test
    public void testSubMapWithValueTransformer() {
        valueTransformer = TransformerUtils.stringValueTransformer();
        TransformedSortedMap<String, String> transformedMap = TransformedSortedMap.transformingSortedMap(originalMap, keyTransformer, valueTransformer);

        SortedMap<String, String> subMap = transformedMap.subMap("2", "4");

        assertEquals(2, subMap.size());
        assertTrue(subMap.containsKey("2"));
        assertTrue(subMap.containsKey("3"));
        assertEquals("two", subMap.get("2"));
        assertEquals("three", subMap.get("3"));
    }
}
