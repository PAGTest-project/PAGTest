
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiValueMap_putTest {

    private MultiValueMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new MultiValueMap<>(new HashMap<>(), ArrayList::new);
    }

    @Test
    public void testPut_NewKey() {
        String key = "A";
        String value = "AA";
        Object result = map.put(key, value);
        assertEquals(value, result);
        Collection<String> collection = map.getCollection(key);
        assertNotNull(collection);
        assertTrue(collection.contains(value));
    }

    @Test
    public void testPut_ExistingKey() {
        String key = "A";
        String value1 = "AA";
        String value2 = "AB";
        map.put(key, value1);
        Object result = map.put(key, value2);
        assertEquals(value2, result);
        Collection<String> collection = map.getCollection(key);
        assertNotNull(collection);
        assertTrue(collection.contains(value1));
        assertTrue(collection.contains(value2));
    }

    @Test
    public void testPut_EmptyCollection() {
        String key = "A";
        String value = "AA";
        // Mocking a collection that returns empty when created
        MultiValueMap<String, String> map = new MultiValueMap<>(new HashMap<>(), () -> new ArrayList<>());
        Object result = map.put(key, value);
        assertNull(result);
        Collection<String> collection = map.getCollection(key);
        assertNotNull(collection);
        assertTrue(collection.isEmpty());
    }
}
