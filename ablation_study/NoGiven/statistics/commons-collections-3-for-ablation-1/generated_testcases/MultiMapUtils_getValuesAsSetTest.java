
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class MultiMapUtils_getValuesAsSetTest {

    @Test
    void testGetValuesAsSet_WithNonNullMapAndSet() {
        MultiValuedMap<String, String> map = MultiMapUtils.newSetValuedHashMap();
        map.put("key1", "value1");
        map.put("key1", "value2");

        Set<String> result = MultiMapUtils.getValuesAsSet(map, "key1");
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("value1"));
        assertTrue(result.contains("value2"));
    }

    @Test
    void testGetValuesAsSet_WithNonNullMapAndNonSet() {
        MultiValuedMap<String, String> map = MultiMapUtils.newListValuedHashMap();
        map.put("key1", "value1");
        map.put("key1", "value2");

        Set<String> result = MultiMapUtils.getValuesAsSet(map, "key1");
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("value1"));
        assertTrue(result.contains("value2"));
    }

    @Test
    void testGetValuesAsSet_WithNullMap() {
        Set<String> result = MultiMapUtils.getValuesAsSet(null, "key1");
        assertNull(result);
    }
}
