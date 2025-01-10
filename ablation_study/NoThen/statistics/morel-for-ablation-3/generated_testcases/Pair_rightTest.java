
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pair_rightTest {

    @Test
    public void testRight() {
        // Given
        List<Map.Entry<String, Integer>> pairs = Arrays.asList(
            new AbstractMap.SimpleEntry<>("a", 1),
            new AbstractMap.SimpleEntry<>("b", 2),
            new AbstractMap.SimpleEntry<>("c", 3)
        );

        // When
        List<Integer> result = Pair.right(pairs);

        // Then
        assertEquals(Arrays.asList(1, 2, 3), result);
    }
}
