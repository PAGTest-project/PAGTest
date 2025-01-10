
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MultiMapUtils_getCollectionTest {

    @Test
    void testGetCollection_WithNonNullMap() {
        MultiValuedMap<String, Integer> map = mock(MultiValuedMap.class);
        Collection<Integer> expectedCollection = new ArrayList<>();
        when(map.get("key")).thenReturn(expectedCollection);

        Collection<Integer> result = MultiMapUtils.getCollection(map, "key");

        assertSame(expectedCollection, result);
    }

    @Test
    void testGetCollection_WithNullMap() {
        Collection<Integer> result = MultiMapUtils.getCollection(null, "key");

        assertNull(result);
    }
}
