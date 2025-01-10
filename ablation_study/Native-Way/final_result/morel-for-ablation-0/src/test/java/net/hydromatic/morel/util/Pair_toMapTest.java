
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Pair_toMapTest {

    @Test
    public void testToMap() {
        Pair<String, Integer> pair1 = new Pair<>("key1", 1);
        Pair<String, Integer> pair2 = new Pair<>("key2", 2);
        Iterable<Pair<String, Integer>> pairs = Arrays.asList(pair1, pair2);

        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("key1", 1);
        expectedMap.put("key2", 2);

        Map<String, Integer> resultMap = Pair.toMap(pairs);

        assertEquals(expectedMap, resultMap);
    }
}
