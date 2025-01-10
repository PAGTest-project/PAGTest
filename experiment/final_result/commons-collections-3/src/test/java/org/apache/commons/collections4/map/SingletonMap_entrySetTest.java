
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.keyvalue.TiedMapEntry;
import org.junit.jupiter.api.Test;

public class SingletonMap_entrySetTest {

    @Test
    public void testEntrySet() {
        SingletonMap<String, String> singletonMap = new SingletonMap<>("key", "value");
        Set<Map.Entry<String, String>> entrySet = singletonMap.entrySet();

        assertEquals(1, entrySet.size());
        assertTrue(entrySet.iterator().next() instanceof TiedMapEntry);
        TiedMapEntry<String, String> entry = (TiedMapEntry<String, String>) entrySet.iterator().next();
        assertEquals("key", entry.getKey());
        assertEquals("value", entry.getValue());
    }
}
