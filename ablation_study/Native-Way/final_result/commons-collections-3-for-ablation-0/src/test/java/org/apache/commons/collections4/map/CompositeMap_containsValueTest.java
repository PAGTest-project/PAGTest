
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_containsValueTest {

    private CompositeMap<String, String> compositeMap;
    private Map<String, String> map1;
    private Map<String, String> map2;

    @BeforeEach
    public void setUp() {
        map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        map2 = new HashMap<>();
        map2.put("key3", "value3");
        map2.put("key4", "value4");

        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testContainsValue_ValuePresentInFirstMap() {
        assertTrue(compositeMap.containsValue("value1"));
    }

    @Test
    public void testContainsValue_ValuePresentInSecondMap() {
        assertTrue(compositeMap.containsValue("value3"));
    }

    @Test
    public void testContainsValue_ValueNotPresent() {
        assertFalse(compositeMap.containsValue("value5"));
    }

    @Test
    public void testContainsValue_NullValuePresent() {
        map1.put("key5", null);
        assertTrue(compositeMap.containsValue(null));
    }

    @Test
    public void testContainsValue_NullValueNotPresent() {
        assertFalse(compositeMap.containsValue(null));
    }
}
