
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
        map.put("a", 1);
        map.put("b", 2);

        Map<String, Integer> result = Static.plus(map, "c", 3);

        assertEquals(ImmutableMap.of("a", 1, "b", 2, "c", 3), result);
    }
}
