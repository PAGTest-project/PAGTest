
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_putAllTest {

    private MultiValueMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new MultiValueMap<>();
    }

    @Test
    public void testPutAll_NullValues() {
        assertFalse(map.putAll("key", null));
    }

    @Test
    public void testPutAll_EmptyValues() {
        Collection<String> values = new ArrayList<>();
        assertFalse(map.putAll("key", values));
    }

    @Test
    public void testPutAll_NewKey() {
        Collection<String> values = Arrays.asList("value1", "value2");
        assertTrue(map.putAll("key", values));
        assertEquals(2, map.size("key"));
    }

    @Test
    public void testPutAll_ExistingKey() {
        Collection<String> initialValues = Arrays.asList("value1", "value2");
        map.putAll("key", initialValues);
        Collection<String> additionalValues = Arrays.asList("value3", "value4");
        assertTrue(map.putAll("key", additionalValues));
        assertEquals(4, map.size("key"));
    }

    @Test
    public void testPutAll_CreateCollection() {
        Collection<String> values = Arrays.asList("value1", "value2");
        map.putAll("key", values);
        assertNotNull(map.getCollection("key"));
    }

    @Test
    public void testPutAll_AddToExistingCollection() {
        Collection<String> initialValues = Arrays.asList("value1", "value2");
        map.putAll("key", initialValues);
        Collection<String> additionalValues = Arrays.asList("value3", "value4");
        map.putAll("key", additionalValues);
        Collection<String> combinedValues = map.getCollection("key");
        assertEquals(4, combinedValues.size());
        assertTrue(combinedValues.containsAll(Arrays.asList("value1", "value2", "value3", "value4")));
    }

    @Test
    public void testPutAll_NonEmptyCollection() {
        Collection<String> values = Arrays.asList("value1", "value2");
        map.putAll("key", values);
        assertNotNull(map.getCollection("key"));
        assertFalse(map.getCollection("key").isEmpty());
    }

    @Test
    public void testPutAll_EmptyCollectionAfterAdd() {
        Collection<String> values = new ArrayList<>();
        map.putAll("key", values);
        assertNull(map.getCollection("key"));
    }

    @Test
    public void testPutAll_MultipleKeys() {
        Collection<String> values1 = Arrays.asList("value1", "value2");
        Collection<String> values2 = Arrays.asList("value3", "value4");
        assertTrue(map.putAll("key1", values1));
        assertTrue(map.putAll("key2", values2));
        assertEquals(2, map.size("key1"));
        assertEquals(2, map.size("key2"));
    }

    @Test
    public void testPutAll_ClearBeforeAdd() {
        Collection<String> values = Arrays.asList("value1", "value2");
        map.putAll("key", values);
        map.clear();
        assertFalse(map.putAll("key", values));
    }

    @Test
    public void testPutAll_RemoveMapping() {
        Collection<String> values = Arrays.asList("value1", "value2");
        map.putAll("key", values);
        assertTrue(map.removeMapping("key", "value1"));
        assertEquals(1, map.size("key"));
    }

    @Test
    public void testPutAll_IteratorInteraction() {
        Collection<String> values = Arrays.asList("value1", "value2");
        map.putAll("key", values);
        Iterator<String> iterator = map.iterator("key");
        assertTrue(iterator.hasNext());
        assertEquals("value1", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("value2", iterator.next());
        assertFalse(iterator.hasNext());
    }
}
