
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ListOrderedMap_previousKeyTest {

    private ListOrderedMap<String, String> map;

    @BeforeEach
    void setUp() {
        map = new ListOrderedMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
    }

    @Test
    void testPreviousKey_existingKey() {
        assertEquals("key2", map.previousKey("key3"));
    }

    @Test
    void testPreviousKey_firstKey() {
        assertNull(map.previousKey("key1"));
    }

    @Test
    void testPreviousKey_nonExistingKey() {
        assertNull(map.previousKey("nonExistingKey"));
    }
}
