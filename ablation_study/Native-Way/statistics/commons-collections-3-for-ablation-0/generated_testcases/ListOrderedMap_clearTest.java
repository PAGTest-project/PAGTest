
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListOrderedMap_clearTest {

    @Test
    public void testClear() {
        ListOrderedMap<String, String> map = new ListOrderedMap<>(new HashMap<>());
        map.put("key1", "value1");
        map.put("key2", "value2");

        map.clear();

        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }
}
