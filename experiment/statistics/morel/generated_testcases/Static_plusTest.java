
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Static_plusTest {

    @Test
    public void testPlus() {
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);

        Map<String, Integer> result = Static.plus(map, "key3", 3);

        assertEquals(3, result.size());
        assertEquals(1, result.get("key1"));
        assertEquals(2, result.get("key2"));
        assertEquals(3, result.get("key3"));
    }
}
