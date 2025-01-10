
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Collection;

class MultiMapUtils_getCollectionTest {

    @Test
    void testGetCollection_WithNonNullMap() {
        MultiValuedMap<String, String> mockMap = mock(MultiValuedMap.class);
        Collection<String> mockCollection = mock(Collection.class);
        when(mockMap.get("key")).thenReturn(mockCollection);

        Collection<String> result = MultiMapUtils.getCollection(mockMap, "key");

        assertNotNull(result);
        assertEquals(mockCollection, result);
    }

    @Test
    void testGetCollection_WithNullMap() {
        Collection<String> result = MultiMapUtils.getCollection(null, "key");

        assertNull(result);
    }
}
