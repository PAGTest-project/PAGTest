
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformedSortedMap_tailMapTest {

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
    public void testTailMap() {
        TransformedSortedMap<String, String> transformedMap = new TransformedSortedMap<>(baseMap, keyTransformer, valueTransformer);
        SortedMap<String, String> tailMap = transformedMap.tailMap("B");

        assertEquals(2, tailMap.size());
        assertEquals("2", tailMap.get("B"));
        assertEquals("3", tailMap.get("C"));
    }

    @Test
    public void testTailMapWithNullKey() {
        TransformedSortedMap<String, String> transformedMap = new TransformedSortedMap<>(baseMap, keyTransformer, valueTransformer);
        assertThrows(NullPointerException.class, () -> {
            transformedMap.tailMap(null);
        });
    }

    @Test
    public void testTailMapWithNonExistentKey() {
        TransformedSortedMap<String, String> transformedMap = new TransformedSortedMap<>(baseMap, keyTransformer, valueTransformer);
        SortedMap<String, String> tailMap = transformedMap.tailMap("D");

        assertEquals(0, tailMap.size());
    }
}
