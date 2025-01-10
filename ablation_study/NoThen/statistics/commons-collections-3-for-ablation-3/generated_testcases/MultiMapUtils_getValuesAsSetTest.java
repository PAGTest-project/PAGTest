
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.junit.jupiter.api.Test;

public class MultiMapUtils_getValuesAsSetTest {

    @Test
    public void testGetValuesAsSet_WithNullMap() {
        MultiValuedMap<String, String> map = null;
        Set<String> result = MultiMapUtils.getValuesAsSet(map, "key");
        assertNull(result);
    }

    @Test
    public void testGetValuesAsSet_WithEmptyMap() {
        MultiValuedMap<String, String> map = MultiMapUtils.emptyMultiValuedMap();
        Set<String> result = MultiMapUtils.getValuesAsSet(map, "key");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetValuesAsSet_WithArrayListValuedHashMap() {
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        map.put("key", "value1");
        map.put("key", "value2");
        Set<String> result = MultiMapUtils.getValuesAsSet(map, "key");
        assertEquals(new HashSet<>(Arrays.asList("value1", "value2")), result);
    }

    @Test
    public void testGetValuesAsSet_WithHashSetValuedHashMap() {
        MultiValuedMap<String, String> map = new HashSetValuedHashMap<>();
        map.put("key", "value1");
        map.put("key", "value2");
        Set<String> result = MultiMapUtils.getValuesAsSet(map, "key");
        assertEquals(new HashSet<>(Arrays.asList("value1", "value2")), result);
    }

    @Test
    public void testGetValuesAsSet_WithNonExistentKey() {
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        map.put("key1", "value1");
        Set<String> result = MultiMapUtils.getValuesAsSet(map, "key2");
        assertTrue(result.isEmpty());
    }
}
