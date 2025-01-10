
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Pair_leftTest {

    @Test
    void testLeft() {
        // Given
        List<Pair<String, Integer>> pairs = Arrays.asList(
            new Pair<>("A", 1),
            new Pair<>("B", 2),
            new Pair<>("C", 3)
        );

        // When
        List<String> result = Pair.left(pairs);

        // Then
        assertEquals(Arrays.asList("A", "B", "C"), result);
    }
}
