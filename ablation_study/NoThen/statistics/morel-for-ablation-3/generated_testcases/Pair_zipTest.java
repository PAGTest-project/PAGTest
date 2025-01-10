
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Pair_zipTest {

    @Test
    void testZipStrictEqualSizes() {
        List<Integer> ks = Arrays.asList(1, 2, 3);
        List<String> vs = Arrays.asList("a", "b", "c");
        List<Pair<Integer, String>> result = Pair.zip(ks, vs, true);
        assertEquals(3, result.size());
        assertEquals(Pair.of(1, "a"), result.get(0));
        assertEquals(Pair.of(2, "b"), result.get(1));
        assertEquals(Pair.of(3, "c"), result.get(2));
    }

    @Test
    void testZipStrictDifferentSizes() {
        List<Integer> ks = Arrays.asList(1, 2, 3);
        List<String> vs = Arrays.asList("a", "b");
        assertThrows(AssertionError.class, () -> Pair.zip(ks, vs, true));
    }

    @Test
    void testZipNonStrict() {
        List<Integer> ks = Arrays.asList(1, 2, 3);
        List<String> vs = Arrays.asList("a", "b");
        List<Pair<Integer, String>> result = Pair.zip(ks, vs, false);
        assertEquals(2, result.size());
        assertEquals(Pair.of(1, "a"), result.get(0));
        assertEquals(Pair.of(2, "b"), result.get(1));
    }
}
