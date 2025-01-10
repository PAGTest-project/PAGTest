
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedMap_indexOfTest {

    private LinkedMap<String, String> linkedMap;

    @BeforeEach
    public void setUp() {
        linkedMap = new LinkedMap<>();
        linkedMap.put("key1", "value1");
        linkedMap.put("key2", "value2");
        linkedMap.put("key3", "value3");
    }

    @Test
    public void testIndexOfExistingKey() {
        assertEquals(0, linkedMap.indexOf("key1"));
        assertEquals(1, linkedMap.indexOf("key2"));
        assertEquals(2, linkedMap.indexOf("key3"));
    }

    @Test
    public void testIndexOfNonExistingKey() {
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, linkedMap.indexOf("nonExistingKey"));
    }

    @Test
    public void testIndexOfNullKey() {
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, linkedMap.indexOf(null));
    }

    @Test
    public void testIndexOfAfterRemove() {
        linkedMap.remove("key2");
        assertEquals(0, linkedMap.indexOf("key1"));
        assertEquals(1, linkedMap.indexOf("key3"));
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, linkedMap.indexOf("key2"));
    }

    @Test
    public void testIndexOfAfterAdd() {
        linkedMap.put("key4", "value4");
        assertEquals(3, linkedMap.indexOf("key4"));
    }
}
