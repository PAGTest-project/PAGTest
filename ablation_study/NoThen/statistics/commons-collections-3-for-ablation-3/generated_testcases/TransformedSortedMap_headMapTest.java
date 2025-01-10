
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        SortedMap<String, String> headMap = transformedMap.headMap("B");

        assertEquals(1, headMap.size());
        assertEquals("1", headMap.get("A"));
    }

    @Test
    public void testHeadMapWithEmptyMap() {
        baseMap.clear();
        TransformedSortedMap<String, String> transformedMap = new TransformedSortedMap<>(baseMap, keyTransformer, valueTransformer);
        SortedMap<String, String> headMap = transformedMap.headMap("B");

        assertEquals(0, headMap.size());
    }

    @Test
    public void testHeadMapWithNullKey() {
        TransformedSortedMap<String, String> transformedMap = new TransformedSortedMap<>(baseMap, keyTransformer, valueTransformer);
        assertThrows(NullPointerException.class, () -> {
            transformedMap.headMap(null);
        });
    }

    @Test
    public void testHeadMapWithNonExistentKey() {
        TransformedSortedMap<String, String> transformedMap = new TransformedSortedMap<>(baseMap, keyTransformer, valueTransformer);
        SortedMap<String, String> headMap = transformedMap.headMap("Z");

        assertEquals(3, headMap.size());
        assertEquals("1", headMap.get("A"));
        assertEquals("2", headMap.get("B"));
        assertEquals("3", headMap.get("C"));
    }
}
