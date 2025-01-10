
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
            Pair.of("one", 1),
            Pair.of("two", 2),
            Pair.of("three", 3)
        );

        // When
        Map<String, Integer> result = Pair.toMap(pairs);

        // Then
        Map<String, Integer> expected = new HashMap<>();
        expected.put("one", 1);
        expected.put("two", 2);
        expected.put("three", 3);

        assertEquals(expected, result);
    }
}
