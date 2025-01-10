
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedMap_indexOfTest {

    private LinkedMap<Integer, String> linkedMap;

    @BeforeEach
    public void setUp() {
        linkedMap = new LinkedMap<>();
        linkedMap.put(1, "One");
        linkedMap.put(2, "Two");
        linkedMap.put(3, "Three");
    }

    @Test
    public void testIndexOfExistingKey() {
        assertEquals(1, linkedMap.indexOf(2));
    }

    @Test
    public void testIndexOfNonExistingKey() {
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, linkedMap.indexOf(4));
    }

    @Test
    public void testIndexOfNullKey() {
        linkedMap.put(null, "Null");
        assertEquals(3, linkedMap.indexOf(null));
    }

    @Test
    public void testIndexOfAfterRemove() {
        linkedMap.remove(2);
        assertEquals(CollectionUtils.INDEX_NOT_FOUND, linkedMap.indexOf(2));
    }

    @Test
    public void testIndexOfAfterAdd() {
        linkedMap.put(4, "Four");
        assertEquals(3, linkedMap.indexOf(4));
    }
}
