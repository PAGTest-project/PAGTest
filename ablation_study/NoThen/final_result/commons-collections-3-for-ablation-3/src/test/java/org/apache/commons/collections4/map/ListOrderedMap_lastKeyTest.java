
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class ListOrderedMap_lastKeyTest {

    @Test
    void testLastKey_EmptyMap() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>();
        assertThrows(NoSuchElementException.class, map::lastKey);
    }

    @Test
    void testLastKey_NonEmptyMap() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertEquals("key2", map.lastKey());
    }
}
