
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class ListOrderedMap_firstKeyTest {

    @Test
    void testFirstKey_MapNotEmpty() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertEquals("key1", map.firstKey());
    }

    @Test
    void testFirstKey_MapEmpty() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>();
        assertThrows(NoSuchElementException.class, map::firstKey);
    }
}
