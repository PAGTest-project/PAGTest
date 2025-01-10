
package org.apache.commons.collections4;

import org.apache.commons.collections4.bag.HashBag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Collection;

class MultiMapUtils_getValuesAsBagTest {

    @Test
    void testGetValuesAsBag_WithNullMap() {
        Bag<String> result = MultiMapUtils.getValuesAsBag(null, "key");
        assertNull(result);
    }

    @Test
    void testGetValuesAsBag_WithNonBagCollection() {
        MultiValuedMap<String, String> map = mock(MultiValuedMap.class);
        Collection<String> collection = mock(Collection.class);
        when(map.get("key")).thenReturn(collection);
        when(collection.iterator()).thenReturn(mock(java.util.Iterator.class));

        Bag<String> result = MultiMapUtils.getValuesAsBag(map, "key");
        assertNotNull(result);
        assertTrue(result instanceof HashBag);
    }

    @Test
    void testGetValuesAsBag_WithBagCollection() {
        MultiValuedMap<String, String> map = mock(MultiValuedMap.class);
        Bag<String> bag = mock(Bag.class);
        when(map.get("key")).thenReturn(bag);

        Bag<String> result = MultiMapUtils.getValuesAsBag(map, "key);
        assertSame(bag, result);
    }
}
