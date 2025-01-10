
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Pair_rightTest {

    @Test
    void testRight() {
        // Given
        List<Map.Entry<String, Integer>> pairs = Arrays.asList(
            Pair.of("one", 1),
            Pair.of("two", 2),
            Pair.of("three", 3)
        );

        // When
        List<Integer> result = Pair.right(pairs);

        // Then
        assertEquals(Arrays.asList(1, 2, 3), result);
    }
}
