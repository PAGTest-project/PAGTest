
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class MapEntry_equalsTest {

    @Test
    void testEquals() {
        MapEntry<String, Integer> entry1 = new MapEntry<>("key", 1);
        MapEntry<String, Integer> entry2 = new MapEntry<>("key", 1);
        MapEntry<String, Integer> entry3 = new MapEntry<>("key", 2);
        MapEntry<String, Integer> entry4 = new MapEntry<>("otherKey", 1);

        // Test same instance
        assertTrue(entry1.equals(entry1));

        // Test different instances with same key and value
        assertTrue(entry1.equals(entry2));

        // Test different instances with different value
        assertFalse(entry1.equals(entry3));

        // Test different instances with different key
        assertFalse(entry1.equals(entry4));

        // Test with null
        assertFalse(entry1.equals(null));

        // Test with non-Map.Entry object
        assertFalse(entry1.equals("not a map entry"));
    }
}
