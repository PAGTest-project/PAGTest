
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.AbstractMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Pair_equalsTest {

    @Test
    void testEquals_SameInstance() {
        Pair<String, Integer> pair = new Pair<>("key", 1);
        assertTrue(pair.equals(pair));
    }

    @Test
    void testEquals_DifferentInstanceSameValues() {
        Pair<String, Integer> pair1 = new Pair<>("key", 1);
        Pair<String, Integer> pair2 = new Pair<>("key", 1);
        assertTrue(pair1.equals(pair2));
    }

    @Test
    void testEquals_DifferentInstanceDifferentValues() {
        Pair<String, Integer> pair1 = new Pair<>("key", 1);
        Pair<String, Integer> pair2 = new Pair<>("key", 2);
        assertFalse(pair1.equals(pair2));
    }

    @Test
    void testEquals_NotInstanceOfMapEntry() {
        Pair<String, Integer> pair = new Pair<>("key", 1);
        assertFalse(pair.equals("not a map entry"));
    }

    @Test
    void testEquals_Null() {
        Pair<String, Integer> pair = new Pair<>("key", 1);
        assertFalse(pair.equals(null));
    }

    @Test
    void testEquals_MapEntryWithSameValues() {
        Pair<String, Integer> pair = new Pair<>("key", 1);
        Map.Entry<String, Integer> mapEntry = new AbstractMap.SimpleEntry<>("key", 1);
        assertTrue(pair.equals(mapEntry));
    }

    @Test
    void testEquals_MapEntryWithDifferentValues() {
        Pair<String, Integer> pair = new Pair<>("key", 1);
        Map.Entry<String, Integer> mapEntry = new AbstractMap.SimpleEntry<>("key", 2);
        assertFalse(pair.equals(mapEntry));
    }
}
