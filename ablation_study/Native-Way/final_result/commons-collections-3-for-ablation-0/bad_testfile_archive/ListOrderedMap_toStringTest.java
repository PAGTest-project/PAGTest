
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

class ListOrderedMap_toStringTest {

    @Test
    void testToStringEmptyMap() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>(new HashMap<>());
        assertEquals("{}", map.toString());
    }

    @Test
    void testToStringNonEmptyMap() {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        ListOrderedMap<String, String> map = new ListOrderedMap<>(hashMap);
        assertEquals("{key1=value1, key2=value2}", map.toString());
    }

    @Test
    void testToStringWithThisMapAsKeyAndValue() {
        ListOrderedMap<Object, Object> map = new ListOrderedMap<>(new HashMap<>());
        map.put(map, map);
        assertEquals("{(this Map)=(this Map)}", map.toString());
    }
}
