
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.AbstractMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Pair_equalsTest {

    @Test
    void testEquals_SameInstance() {
        Pair<String, Integer> pair = Pair.of("key", 42);
        assertTrue(pair.equals(pair));
    }

    @Test
    void testEquals_DifferentInstanceSameValues() {
        Pair<String, Integer> pair1 = Pair.of("key", 42);
        Pair<String, Integer> pair2 = Pair.of("key", 42);
        assertTrue(pair1.equals(pair2));
    }

    @Test
    void testEquals_DifferentInstanceDifferentValues() {
        Pair<String, Integer> pair1 = Pair.of("key", 42);
        Pair<String, Integer> pair2 = Pair.of("key", 43);
        assertFalse(pair1.equals(pair2));
    }

    @Test
    void testEquals_NotInstanceOfMapEntry() {
        Pair<String, Integer> pair = Pair.of("key", 42);
        assertFalse(pair.equals("not a map entry"));
    }

    @Test
    void testEquals_Null() {
        Pair<String, Integer> pair = Pair.of("key", 42);
        assertFalse(pair.equals(null));
    }

    @Test
    void testEquals_MapEntrySameValues() {
        Pair<String, Integer> pair = Pair.of("key", 42);
        Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>("key", 42);
        assertTrue(pair.equals(entry));
    }

    @Test
    void testEquals_MapEntryDifferentValues() {
        Pair<String, Integer> pair = Pair.of("key", 42);
        Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>("key", 43);
        assertFalse(pair.equals(entry));
    }
}
