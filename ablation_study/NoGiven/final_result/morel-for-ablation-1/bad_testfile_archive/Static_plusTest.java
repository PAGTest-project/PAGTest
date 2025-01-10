
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Static_plusTest {

    @Test
    public void testPlus() {
        // Given
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);

        // When
        Map<String, Integer> result = Pair.plus(map, "key3", 3);

        // Then
        Map<String, Integer> expected = ImmutableMap.<String, Integer>builder()
                .put("key1", 1)
                .put("key2", 2)
                .put("key3", 3)
                .build();

        assertEquals(expected, result);
    }
}
