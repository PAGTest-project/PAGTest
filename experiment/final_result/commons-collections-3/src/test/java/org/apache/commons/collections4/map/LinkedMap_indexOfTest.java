
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
    }

    @Test
    public void testIndexOf_KeyFound() {
        linkedMap.put("key1", "value1");
        linkedMap.put("key2", "value2");
        linkedMap.put("key3", "value3");

        assertEquals(1, linkedMap.indexOf("key2"));
    }

    @Test
    public void testIndexOf_KeyNotFound() {
        linkedMap.put("key1", "value1");
        linkedMap.put("key2", "value2");

        assertEquals(CollectionUtils.INDEX_NOT_FOUND, linkedMap.indexOf("key3"));
    }
}
