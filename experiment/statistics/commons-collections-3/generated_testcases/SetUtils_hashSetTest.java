
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;

public class SetUtils_hashSetTest {

    @Test
    public void testHashSetWithItems() {
        String[] items = {"item1", "item2", "item3"};
        HashSet<String> result = SetUtils.hashSet(items);
        assertEquals(3, result.size());
        assertTrue(result.contains("item1"));
        assertTrue(result.contains("item2"));
        assertTrue(result.contains("item3"));
    }

    @Test
    public void testHashSetWithNull() {
        HashSet<String> result = SetUtils.hashSet(null);
        assertNull(result);
    }

    @Test
    public void testHashSetWithEmptyItems() {
        String[] items = {};
        HashSet<String> result = SetUtils.hashSet(items);
        assertEquals(0, result.size());
    }
}
