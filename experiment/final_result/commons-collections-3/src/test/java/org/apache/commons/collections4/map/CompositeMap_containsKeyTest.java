
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_containsKeyTest {

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
    public void testContainsKey_KeyPresentInFirstMap() {
        assertTrue(compositeMap.containsKey("key1"));
    }

    @Test
    public void testContainsKey_KeyPresentInSecondMap() {
        assertTrue(compositeMap.containsKey("key3"));
    }

    @Test
    public void testContainsKey_KeyNotPresent() {
        assertFalse(compositeMap.containsKey("key5"));
    }

    @Test
    public void testContainsKey_NullKey() {
        assertFalse(compositeMap.containsKey(null));
    }
}
