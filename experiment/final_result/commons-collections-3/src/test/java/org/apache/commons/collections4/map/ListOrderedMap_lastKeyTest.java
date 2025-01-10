
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ListOrderedMap_lastKeyTest {

    private ListOrderedMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new ListOrderedMap<>();
    }

    @Test
    public void testLastKey_EmptyMap() {
        assertThrows(NoSuchElementException.class, () -> map.lastKey());
    }

    @Test
    public void testLastKey_NonEmptyMap() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        assertEquals("key3", map.lastKey());
    }
}
