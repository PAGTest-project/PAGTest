
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MultiValueMap_clearTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>();
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");
    }

    @Test
    public void testClear() {
        multiValueMap.clear();
        assertTrue(multiValueMap.isEmpty());
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testClearWithIterator() {
        Iterator<Map.Entry<String, Collection<String>>> iterator = multiValueMap.entrySet().iterator();
        multiValueMap.clear();
        assertFalse(iterator.hasNext());
        assertTrue(multiValueMap.isEmpty());
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testClearWithValuesView() {
        Collection<String> valuesView = multiValueMap.values();
        multiValueMap.clear();
        assertTrue(valuesView.isEmpty());
        assertTrue(multiValueMap.isEmpty());
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testClearWithCreateCollection() {
        multiValueMap.clear();
        Collection<String> newCollection = multiValueMap.createCollection(1);
        assertNotNull(newCollection);
        assertTrue(newCollection instanceof ArrayList);
        assertTrue(multiValueMap.isEmpty());
        assertEquals(0, multiValueMap.totalSize());
    }

    @Test
    public void testClearWithPut() {
        multiValueMap.clear();
        multiValueMap.put("newKey", "newValue");
        assertFalse(multiValueMap.isEmpty());
        assertEquals(1, multiValueMap.totalSize());
    }
}
