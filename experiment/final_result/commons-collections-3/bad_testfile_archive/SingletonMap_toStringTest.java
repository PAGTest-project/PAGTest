
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SingletonMap_toStringTest {

    @Test
    public void testToString_KeyAndValueAreNotThisMap() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "value");
        assertEquals("{key=value}", map.toString());
    }

    @Test
    public void testToString_KeyIsThisMap() {
        SingletonMap<SingletonMap<String, String>, String> map = new SingletonMap<>(null, "value");
        map.put(map, "value");
        assertEquals("{(this Map)=value}", map.toString());
    }

    @Test
    public void testToString_ValueIsThisMap() {
        SingletonMap<String, SingletonMap<String, String>> map = new SingletonMap<>("key", null);
        map.put("key", map);
        assertEquals("{key=(this Map)}", map.toString());
    }

    @Test
    public void testToString_KeyAndValueAreThisMap() {
        SingletonMap<SingletonMap<String, String>, SingletonMap<String, String>> map = new SingletonMap<>(null, null);
        map.put(map, map);
        assertEquals("{(this Map)=(this Map)}", map.toString());
    }
}
