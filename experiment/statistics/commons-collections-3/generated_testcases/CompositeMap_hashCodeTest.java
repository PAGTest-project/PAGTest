
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_hashCodeTest {

    private CompositeMap<String, String> compositeMap;

    @BeforeEach
    public void setUp() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        Map<String, String> map2 = new HashMap<>();
        map2.put("key3", "value3");
        map2.put("key4", "value4");

        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testHashCode() {
        int expectedHashCode = 0;
        for (Map.Entry<String, String> entry : compositeMap.entrySet()) {
            expectedHashCode += entry.hashCode();
        }

        assertEquals(expectedHashCode, compositeMap.hashCode());
    }
}
