
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collection;
import org.junit.jupiter.api.Test;

public class MultiMapUtils_getCollectionTest {

    @Test
    public void testGetCollection_WithNonNullMap() {
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        map.put("key1", "value1");
        Collection<String> result = MultiMapUtils.getCollection(map, "key1");
        assertEquals(1, result.size());
        assertEquals("value1", result.iterator().next());
    }

    @Test
    public void testGetCollection_WithNullMap() {
        Collection<String> result = MultiMapUtils.getCollection(null, "key1");
        assertNull(result);
    }
}
