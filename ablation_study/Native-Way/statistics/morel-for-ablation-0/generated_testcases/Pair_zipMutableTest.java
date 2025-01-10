
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pair_zipMutableTest {

    @Test
    public void testZipMutable() {
        List<Integer> ks = Arrays.asList(1, 2, 3);
        List<String> vs = Arrays.asList("a", "b", "c");

        List<Pair<Integer, String>> result = Pair.zipMutable(ks, vs);

        assertEquals(3, result.size());
        assertEquals(new Pair<>(1, "a"), result.get(0));
        assertEquals(new Pair<>(2, "b"), result.get(1));
        assertEquals(new Pair<>(3, "c"), result.get(2));
    }
}
