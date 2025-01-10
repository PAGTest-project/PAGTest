
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class SingletonMap_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "value");
        assertTrue(map.equals(map));
    }

    @Test
    public void testEquals_DifferentType() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "value");
        assertFalse(map.equals("not a map"));
    }

    @Test
    public void testEquals_DifferentSizeMap() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "value");
        Map<String, String> other = new HashMap<>();
        other.put("key1", "value1");
        other.put("key2", "value2");
        assertFalse(map.equals(other));
    }

    @Test
    public void testEquals_SameKeyDifferentValue() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "value");
        Map<String, String> other = new HashMap<>();
        other.put("key", "differentValue");
        assertFalse(map.equals(other));
    }

    @Test
    public void testEquals_DifferentKeySameValue() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "value");
        Map<String, String> other = new HashMap<>();
        other.put("differentKey", "value");
        assertFalse(map.equals(other));
    }

    @Test
    public void testEquals_SameKeySameValue() {
        SingletonMap<String, String> map = new SingletonMap<>("key", "value");
        Map<String, String> other = new HashMap<>();
        other.put("key", "value");
        assertTrue(map.equals(other));
    }
}
