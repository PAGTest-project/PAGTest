
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class SingletonMap_entrySetTest {

    @Test
    public void testEntrySet() {
        SingletonMap<String, String> singletonMap = new SingletonMap<>("key", "value");
        Set<Map.Entry<String, String>> entrySet = singletonMap.entrySet();

        assertEquals(1, entrySet.size());
        Map.Entry<String, String> entry = entrySet.iterator().next();
        assertEquals("key", entry.getKey());
        assertEquals("value", entry.getValue());
    }
}
