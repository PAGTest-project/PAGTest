
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
        List<Map.Entry<String, Integer>> pairs = Arrays.asList(
            new AbstractMap.SimpleEntry<>("A", 1),
            new AbstractMap.SimpleEntry<>("B", 2),
            new AbstractMap.SimpleEntry<>("C", 3)
        );

        List<String> leftElements = Pair.left(pairs);

        assertEquals(3, leftElements.size());
        assertEquals("A", leftElements.get(0));
        assertEquals("B", leftElements.get(1));
        assertEquals("C", leftElements.get(2));
    }
}
