
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_getTest {

    private CompositeMap<String, String> compositeMap;
    private Map<String, String> map1;
    private Map<String, String> map2;

    @BeforeEach
    public void setUp() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        compositeMap = new CompositeMap<>(map1, map2);
    }

    @Test
    public void testGetExistingKey() {
        map1.put("key1", "value1");
        map2.put("key2", "value2");
        assertEquals("value1", compositeMap.get("key1"));
        assertEquals("value2", compositeMap.get("key2"));
    }

    @Test
    public void testGetNonExistingKey() {
        map1.put("key1", "value1");
        assertNull(compositeMap.get("key3"));
    }

    @Test
    public void testGetAfterAddComposited() {
        map1.put("key1", "value1");
        Map<String, String> newMap = new HashMap<>();
        newMap.put("key3", "value3");
        compositeMap.addComposited(newMap);
        assertEquals("value3", compositeMap.get("key3"));
    }

    @Test
    public void testGetAfterPut() {
        map1.put("key1", "value1");
        map1.put("key2", "value2");
        assertEquals("value2", compositeMap.get("key2"));
    }
}
