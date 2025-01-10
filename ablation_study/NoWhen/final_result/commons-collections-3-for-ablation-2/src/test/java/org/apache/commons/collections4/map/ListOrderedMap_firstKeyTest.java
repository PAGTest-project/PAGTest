
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    public void testFirstKey_AfterRemoveFirstKey() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        listOrderedMap.remove("key1");
        assertEquals("key2", listOrderedMap.firstKey());
    }

    @Test
    public void testFirstKey_AfterAddNewKey() {
        listOrderedMap.put("key1", "value1");
        listOrderedMap.put("key2", "value2");
        listOrderedMap.put("key3", "value3");
        assertEquals("key1", listOrderedMap.firstKey());
    }
}
