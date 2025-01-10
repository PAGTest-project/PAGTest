
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pair_leftTest {

    @Test
    public void testLeft() {
        // Given
        List<Map.Entry<String, Integer>> pairs = Arrays.asList(
            new AbstractMap.SimpleEntry<>("a", 1),
            new AbstractMap.SimpleEntry<>("b", 2),
            new AbstractMap.SimpleEntry<>("c", 3)
        );

        // When
        List<String> result = Pair.left(pairs);

        // Then
        assertEquals(Arrays.asList("a", "b", "c"), result);
    }
}
