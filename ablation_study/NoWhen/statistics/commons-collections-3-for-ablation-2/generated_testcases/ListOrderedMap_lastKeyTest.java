
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOrderedMap_lastKeyTest {
    private ListOrderedMap<String, String> listOrderedMap;

    @BeforeEach
    public void setUp() {
        listOrderedMap = new ListOrderedMap<>();
    }

    @Test
    public void testLastKey_EmptyMap() {
        assertThrows(NoSuchElementException.class, () -> listOrderedMap.lastKey());
    }

    @Test
    public void testLastKey_NonEmptyMap() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        listOrderedMap.put("key3", "value3");

        assertEquals("key3", listOrderedMap.lastKey());
    }

    @Test
    public void testLastKey_AfterRemoval() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        listOrderedMap.put("key3", "value3");

        listOrderedMap.remove("key3");

        assertEquals("key2", listOrderedMap.lastKey());
    }
}
