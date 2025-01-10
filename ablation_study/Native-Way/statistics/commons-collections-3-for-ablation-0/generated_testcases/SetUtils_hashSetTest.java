
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class SetUtils_hashSetTest {

    @Test
    public void testHashSetWithItems() {
        Set<String> result = SetUtils.hashSet("a", "b", "c");
        assertEquals(3, result.size());
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("c"));
    }

    @Test
    public void testHashSetWithNoItems() {
        Set<String> result = SetUtils.hashSet();
        assertEquals(0, result.size());
        assertTrue(result.isEmpty());
    }

    @Test
    public void testHashSetWithNull() {
        Set<String> result = SetUtils.hashSet((String[]) null);
        assertNull(result);
    }

    @Test
    public void testHashSetWithDuplicates() {
        Set<String> result = SetUtils.hashSet("a", "b", "a", "c", "b");
        assertEquals(3, result.size());
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("c"));
    }
}
