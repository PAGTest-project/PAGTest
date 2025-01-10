
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.apache.commons.collections4.collection.TransformedCollectionTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformedMap_putAllTest {

    private TransformedMap<String, String> transformedMap;
    private Map<String, String> baseMap;

    @BeforeEach
    public void setUp() {
        baseMap = new HashMap<>();
        baseMap.put("A", "1");
        baseMap.put("B", "2");
        baseMap.put("C", "3");

        transformedMap = TransformedMap.transformedMap(
                baseMap,
                (Transformer<? super String, ? extends String>) TransformedCollectionTest.TO_LOWER_CASE_TRANSFORMER,
                (Transformer<? super String, ? extends String>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
    }

    @Test
    public void testPutAll() {
        Map<String, String> mapToCopy = new HashMap<>();
        mapToCopy.put("D", "4");
        mapToCopy.put("E", "5");

        transformedMap.putAll(mapToCopy);

        assertEquals(5, transformedMap.size());
        assertEquals(Integer.valueOf(4), transformedMap.get("d"));
        assertEquals(Integer.valueOf(5), transformedMap.get("e"));
    }

    @Test
    public void testPutAllWithEmptyMap() {
        Map<String, String> mapToCopy = new HashMap<>();

        transformedMap.putAll(mapToCopy);

        assertEquals(3, transformedMap.size());
        assertTrue(transformedMap.containsKey("a"));
        assertTrue(transformedMap.containsKey("b"));
        assertTrue(transformedMap.containsKey("c"));
    }
}
