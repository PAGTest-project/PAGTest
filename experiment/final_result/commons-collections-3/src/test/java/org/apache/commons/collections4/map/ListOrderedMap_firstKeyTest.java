
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOrderedMap_firstKeyTest {
    private ListOrderedMap<String, String> map;

    @BeforeEach
    public void setUp() {
        map = new ListOrderedMap<>();
    }

    @Test
    public void testFirstKey_EmptyMap() {
        assertThrows(NoSuchElementException.class, () -> map.firstKey());
    }

    @Test
    public void testFirstKey_NonEmptyMap() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertEquals("key1", map.firstKey());
    }
}
