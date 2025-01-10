
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOrderedMap_toStringTest {

    @Test
    public void testToStringEmptyMap() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>(new HashMap<>());
        assertEquals("{}", map.toString());
    }

    @Test
    public void testToStringNonEmptyMap() {
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("key1", "value1");
        originalMap.put("key2", "value2");
        ListOrderedMap<String, String> map = new ListOrderedMap<>(originalMap);
        assertEquals("{key1=value1, key2=value2}", map.toString());
    }

    @Test
    public void testToStringWithThisMapAsKeyAndValue() {
        ListOrderedMap<Object, Object> map = new ListOrderedMap<>(new HashMap<>());
        map.put(map, map);
        assertEquals("{(this Map)=(this Map)}", map.toString());
    }
}
