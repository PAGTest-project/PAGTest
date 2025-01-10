
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pair_leftTest {

    @Test
    public void testLeft() {
        List<Map.Entry<String, Integer>> pairs = Arrays.asList(
            Pair.of("a", 1),
            Pair.of("b", 2),
            Pair.of("c", 3)
        );

        List<String> result = Pair.left(pairs);

        assertEquals(3, result.size());
        assertEquals("a", result.get(0));
        assertEquals("b", result.get(1));
        assertEquals("c", result.get(2));
    }
}
