
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapUtils_populateMapTest {

    @Test
    public void testPopulateMap() {
        // Given
        Map<String, Integer> map = new HashMap<>();
        List<String> elements = Arrays.asList("one", "two", "three");
        Function<String, String> keyTransformer = Function.identity();
        Function<String, Integer> valueTransformer = String::length;

        // When
        MapUtils.populateMap(map, elements, keyTransformer::apply, valueTransformer::apply);

        // Then
        assertEquals(3, map.size());
        assertEquals(3, map.get("one"));
        assertEquals(3, map.get("two"));
        assertEquals(5, map.get("three"));
    }
}
