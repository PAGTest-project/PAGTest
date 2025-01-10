
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pair_toMapTest {

    @Test
    public void testToMap() {
        // Given
        List<Pair<String, Integer>> pairs = Arrays.asList(
            new Pair<>("key1", 1),
            new Pair<>("key2", 2),
            new Pair<>("key3", 3)
        );

        // When
        Map<String, Integer> result = Pair.toMap(pairs);

        // Then
        Map<String, Integer> expected = new HashMap<>();
        expected.put("key1", 1);
        expected.put("key2", 2);
        expected.put("key3", 3);

        assertEquals(expected, result);
    }
}
