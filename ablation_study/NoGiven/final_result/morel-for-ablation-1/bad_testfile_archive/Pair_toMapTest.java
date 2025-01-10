
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pair_toMapTest {

    @Test
    public void testToMap() {
        // Given
        List<Pair<String, Integer>> pairs = Arrays.asList(
            new Pair<>("one", 1),
            new Pair<>("two", 2),
            new Pair<>("three", 3)
        );

        // When
        Map<String, Integer> result = Pair.toMap(pairs);

        // Then
        assertEquals(3, result.size());
        assertEquals(Integer.valueOf(1), result.get("one"));
        assertEquals(Integer.valueOf(2), result.get("two"));
        assertEquals(Integer.valueOf(3), result.get("three"));
    }
}
