
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MultiValueMap_iteratorTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>();
    }

    @Test
    public void testIteratorWithSingleEntry() {
        multiValueMap.put("key1", "value1");

        Iterator<Map.Entry<String, String>> iterator = multiValueMap.iterator();
        assertTrue(iterator.hasNext());

        Map.Entry<String, String> entry = iterator.next();
        assertEquals("key1", entry.getKey());
        assertEquals("value1", entry.getValue());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorWithMultipleEntries() {
        multiValueMap.put("key1", "value1");
        multiValueMap.put("key1", "value2");
        multiValueMap.put("key2", "value3");

        Iterator<Map.Entry<String, String>> iterator = multiValueMap.iterator();
        assertTrue(iterator.hasNext());

        Map.Entry<String, String> entry1 = iterator.next();
        assertEquals("key1", entry1.getKey());
        assertTrue(Arrays.asList("value1", "value2").contains(entry1.getValue()));

        assertTrue(iterator.hasNext());

        Map.Entry<String, String> entry2 = iterator.next();
        assertEquals("key1", entry2.getKey());
        assertTrue(Arrays.asList("value1", "value2").contains(entry2.getValue()));

        assertTrue(iterator.hasNext());

        Map.Entry<String, String> entry3 = iterator.next();
        assertEquals("key2", entry3.getKey());
        assertEquals("value3", entry3.getValue());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorWithEmptyMap() {
        Iterator<Map.Entry<String, String>> iterator = multiValueMap.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorWithUnsupportedOperationException() {
        multiValueMap.put("key1", "value1");

        Iterator<Map.Entry<String, String>> iterator = multiValueMap.iterator();
        assertTrue(iterator.hasNext());

        Map.Entry<String, String> entry = iterator.next();
        assertEquals("key1", entry.getKey());
        assertEquals("value1", entry.getValue());

        assertThrows(UnsupportedOperationException.class, () -> entry.setValue("newValue"));
    }
}
