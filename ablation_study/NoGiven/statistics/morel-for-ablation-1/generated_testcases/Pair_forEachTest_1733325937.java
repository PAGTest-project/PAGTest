
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pair_forEachTest {

    @Test
    public void testForEach() {
        List<Integer> ks = Arrays.asList(1, 2, 3);
        List<String> vs = Arrays.asList("a", "b", "c");
        StringBuilder result = new StringBuilder();

        BiConsumer<Integer, String> consumer = (k, v) -> result.append(k).append(v);

        Pair.forEach(ks, vs, consumer);

        assertEquals("1a2b3c", result.toString());
    }
}
