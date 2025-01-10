
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collection;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.junit.jupiter.api.Test;

public class MultiMapUtils_getCollectionTest {

    @Test
    public void testGetCollection_MapIsNull() {
        // Given
        MultiValuedMap<String, String> map = null;
        String key = "testKey";

        // When
        Collection<String> result = MultiMapUtils.getCollection(map, key);

        // Then
        assertNull(result);
    }

    @Test
    public void testGetCollection_MapIsNotNull() {
        // Given
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        String key = "testKey";
        map.put(key, "value1");
        map.put(key, "value2");

        // When
        Collection<String> result = MultiMapUtils.getCollection(map, key);

        // Then
        assertEquals(2, result.size());
        assertEquals("value1", result.iterator().next());
    }
}
