
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformedSortedMap_subMapTest {

    private SortedMap<String, String> baseMap;
    private Transformer<String, String> keyTransformer;
    private Transformer<String, String> valueTransformer;

    @BeforeEach
    public void setUp() {
        baseMap = new TreeMap<>();
        baseMap.put("A", "1");
        baseMap.put("B", "2");
        baseMap.put("C", "3");

        keyTransformer = TransformerUtils.nopTransformer();
        valueTransformer = TransformerUtils.nopTransformer();
    }

    @Test
    public void testSubMap() {
        TransformedSortedMap<String, String> transformedMap = TransformedSortedMap.transformingSortedMap(baseMap, keyTransformer, valueTransformer);

        SortedMap<String, String> subMap = transformedMap.subMap("A", "C");

        assertEquals(2, subMap.size());
        assertEquals("1", subMap.get("A"));
        assertEquals("2", subMap.get("B"));
    }

    @Test
    public void testSubMapWithTransformers() {
        Transformer<String, String> keyTransformer = TransformerUtils.stringValueTransformer();
        Transformer<String, String> valueTransformer = TransformerUtils.stringValueTransformer();

        TransformedSortedMap<String, String> transformedMap = TransformedSortedMap.transformingSortedMap(baseMap, keyTransformer, valueTransformer);

        SortedMap<String, String> subMap = transformedMap.subMap("A", "C");

        assertEquals(2, subMap.size());
        assertEquals("1", subMap.get("A"));
        assertEquals("2", subMap.get("B"));
    }
}
