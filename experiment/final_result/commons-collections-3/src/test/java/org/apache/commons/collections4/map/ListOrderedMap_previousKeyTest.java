
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ListOrderedMap_previousKeyTest {

    private ListOrderedMap<String, String> listOrderedMap;

    @BeforeEach
    public void setUp() {
        listOrderedMap = new ListOrderedMap<>();
    }

    @Test
    public void testPreviousKey_KeyExists() {
        // Given
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        listOrderedMap.put("key3", "value3");

        // When
        String previousKey = listOrderedMap.previousKey("key2");

        // Then
        assertEquals("key1", previousKey);
    }

    @Test
    public void testPreviousKey_KeyDoesNotExist() {
        // Given
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");

        // When
        String previousKey = listOrderedMap.previousKey("key1");

        // Then
        assertNull(previousKey);
    }

    @Test
    public void testPreviousKey_KeyNotInMap() {
        // Given
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");

        // When
        String previousKey = listOrderedMap.previousKey("key4");

        // Then
        assertNull(previousKey);
    }
}
