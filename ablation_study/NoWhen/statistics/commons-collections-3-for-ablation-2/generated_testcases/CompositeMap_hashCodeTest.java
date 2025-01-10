
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompositeMap_hashCodeTest {

    @Test
    public void testHashCodeWithSingleMap() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        CompositeMap<String, String> compositeMap = new CompositeMap<>(map1);

        int expectedHashCode = map1.entrySet().stream().mapToInt(Map.Entry::hashCode).sum();
        assertEquals(expectedHashCode, compositeMap.hashCode());
    }

    @Test
    public void testHashCodeWithMultipleMaps() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        Map<String, String> map2 = new HashMap<>();
        map2.put("key3", "value3");
        map2.put("key4", "value4");

        CompositeMap<String, String> compositeMap = new CompositeMap<>(map1, map2);

        int expectedHashCode = map1.entrySet().stream().mapToInt(Map.Entry::hashCode).sum()
                + map2.entrySet().stream().mapToInt(Map.Entry::hashCode).sum();
        assertEquals(expectedHashCode, compositeMap.hashCode());
    }
}
