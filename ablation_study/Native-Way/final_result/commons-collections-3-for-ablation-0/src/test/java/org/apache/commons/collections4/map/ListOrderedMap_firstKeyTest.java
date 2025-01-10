
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ListOrderedMap_firstKeyTest {
    private ListOrderedMap<String, String> listOrderedMap;

    @BeforeEach
    public void setUp() {
        listOrderedMap = new ListOrderedMap<>();
    }

    @Test
    public void testFirstKey_EmptyMap() {
        assertThrows(NoSuchElementException.class, () -> listOrderedMap.firstKey());
    }

    @Test
    public void testFirstKey_NonEmptyMap() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        assertEquals("key1", listOrderedMap.firstKey());
    }
}
