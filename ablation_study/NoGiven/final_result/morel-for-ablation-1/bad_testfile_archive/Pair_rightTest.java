
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Pair_rightTest {

    @Test
    void testRight() {
        List<Map.Entry<String, Integer>> pairs = Arrays.asList(
            Map.entry("a", 1),
            Map.entry("b", 2),
            Map.entry("c", 3)
        );

        List<Integer> result = Pair.right(pairs);

        assertEquals(Arrays.asList(1, 2, 3), result);
    }
}
