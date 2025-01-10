
package org.apache.commons.collections4;

import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MultiMapUtils_getValuesAsBagTest {

    @Test
    public void testGetValuesAsBag_WithNullMap() {
        // Given
        MultiValuedMap<String, String> map = null;
        String key = "testKey";

        // When
        Bag<String> result = MultiMapUtils.getValuesAsBag(map, key);

        // Then
        assertNull(result);
    }

    @Test
    public void testGetValuesAsBag_WithNonBagCollection() {
        // Given
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        String key = "testKey";
        map.put(key, "value1");
        map.put(key, "value2");

        // When
        Bag<String> result = MultiMapUtils.getValuesAsBag(map, key);

        // Then
        assertEquals(2, result.size());
        assertEquals(1, result.getCount("value1"));
        assertEquals(1, result.getCount("value2"));
    }

    @Test
    public void testGetValuesAsBag_WithBagCollection() {
        // Given
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        String key = "testKey";
        Bag<String> bag = new HashBag<>();
        bag.add("value1", 2);
        bag.add("value2", 3);
        map.putAll(key, bag);

        // When
        Bag<String> result = MultiMapUtils.getValuesAsBag(map, key);

        // Then
        assertEquals(5, result.size());
        assertEquals(2, result.getCount("value1"));
        assertEquals(3, result.getCount("value2"));
    }
}
