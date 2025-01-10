
package org.apache.commons.collections4;

import org.apache.commons.collections4.map.MultiValueMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapUtils_multiValueMapTest {

    @Test
    public void testMultiValueMap() {
        // Given
        Map<String, Collection<String>> inputMap = new HashMap<>();
        inputMap.put("key1", new ArrayList<>());
        inputMap.put("key2", new ArrayList<>());

        // When
        MultiValueMap<String, String> result = MapUtils.multiValueMap(inputMap, ArrayList.class);

        // Then
        assertEquals(inputMap.size(), result.size());
    }
}
