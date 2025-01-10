
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.AbstractMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Pair_equalsTest {

    @Test
    void testEquals_SameInstance() {
        Pair<String, Integer> pair = new Pair<>("key", 42);
        assertTrue(pair.equals(pair));
    }

    @Test
    void testEquals_DifferentType() {
        Pair<String, Integer> pair = new Pair<>("key", 42);
        assertFalse(pair.equals("not a pair"));
    }

    @Test
    void testEquals_SameContent() {
        Pair<String, Integer> pair1 = new Pair<>("key", 42);
        Pair<String, Integer> pair2 = new Pair<>("key", 42);
        assertTrue(pair1.equals(pair2));
    }

    @Test
    void testEquals_DifferentContent() {
        Pair<String, Integer> pair1 = new Pair<>("key", 42);
        Pair<String, Integer> pair2 = new Pair<>("key", 43);
        assertFalse(pair1.equals(pair2));
    }

    @Test
    void testEquals_MapEntrySameContent() {
        Pair<String, Integer> pair = new Pair<>("key", 42);
        Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>("key", 42);
        assertTrue(pair.equals(entry));
    }

    @Test
    void testEquals_MapEntryDifferentContent() {
        Pair<String, Integer> pair = new Pair<>("key", 42);
        Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>("key", 43);
        assertFalse(pair.equals(entry));
    }
}
