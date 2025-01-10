
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
        ListOrderedMap<String, String> map = new ListOrderedMap<>(new HashMap<>());
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertEquals("{key1=value1, key2=value2}", map.toString());
    }

    @Test
    public void testToStringWithSelfReference() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>(new HashMap<>());
        map.put("key1", "value1");
        map.put("key2", map.toString());
        assertEquals("{key1=value1, key2=(this Map)}", map.toString());
    }
}
