
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
        List<Map.Entry<String, Integer>> pairs = Arrays.asList(
            new AbstractMap.SimpleEntry<>("a", 1),
            new AbstractMap.SimpleEntry<>("b", 2),
            new AbstractMap.SimpleEntry<>("c", 3)
        );

        List<Integer> result = Pair.right(pairs);

        assertEquals(3, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(2), result.get(1));
        assertEquals(Integer.valueOf(3), result.get(2));
    }
}
