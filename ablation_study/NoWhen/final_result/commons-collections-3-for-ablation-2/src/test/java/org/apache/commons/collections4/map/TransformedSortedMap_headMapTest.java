
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformedSortedMap_headMapTest {

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
    public void testHeadMap() {
        TransformedSortedMap<String, String> transformedMap = new TransformedSortedMap<>(baseMap, keyTransformer, valueTransformer);
        SortedMap<String, String> headMap = transformedMap.headMap("C");

        assertEquals(2, headMap.size());
        assertTrue(headMap.containsKey("A"));
        assertTrue(headMap.containsKey("B"));
        assertTrue(headMap.containsValue("1"));
        assertTrue(headMap.containsValue("2"));
    }

    @Test
    public void testHeadMapWithTransformers() {
        Transformer<String, String> keyTransformer = TransformerUtils.stringValueTransformer();
        Transformer<String, String> valueTransformer = TransformerUtils.stringValueTransformer();

        TransformedSortedMap<String, String> transformedMap = new TransformedSortedMap<>(baseMap, keyTransformer, valueTransformer);
        SortedMap<String, String> headMap = transformedMap.headMap("C");

        assertEquals(2, headMap.size());
        assertTrue(headMap.containsKey("A"));
        assertTrue(headMap.containsKey("B"));
        assertTrue(headMap.containsValue("1"));
        assertTrue(headMap.containsValue("2"));
    }
}
