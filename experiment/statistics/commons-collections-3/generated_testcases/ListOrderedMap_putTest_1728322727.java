
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOrderedMap_putTest {

    private ListOrderedMap<Integer, String> listOrderedMap;

    @BeforeEach
    public void setUp() {
        listOrderedMap = new ListOrderedMap<>(new HashMap<>());
    }

    @Test
    public void testPutAtIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            listOrderedMap.put(-1, 1, "value");
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            listOrderedMap.put(1, 1, "value");
        });
    }

    @Test
    public void testPutNewKey() {
        String result = listOrderedMap.put(0, 1, "value1");
        assertNull(result);
        assertEquals("value1", listOrderedMap.get(1));
    }

    @Test
    public void testPutExistingKey() {
        listOrderedMap.put(0, 1, "value1");
        String result = listOrderedMap.put(0, 1, "value2");
        assertEquals("value1", result);
        assertEquals("value2", listOrderedMap.get(1));
    }

    @Test
    public void testPutExistingKeyWithDifferentIndex() {
        listOrderedMap.put(0, 1, "value1");
        listOrderedMap.put(1, 2, "value2");
        String result = listOrderedMap.put(1, 1, "value3");
        assertEquals("value1", result);
        assertEquals("value3", listOrderedMap.get(1));
        assertEquals(2, listOrderedMap.size());
    }
}
