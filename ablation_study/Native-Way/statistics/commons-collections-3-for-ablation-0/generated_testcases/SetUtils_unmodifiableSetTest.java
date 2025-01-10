
package org.apache.commons.collections4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SetUtils_unmodifiableSetTest {

    @BeforeEach
    public void setUp() {
        // No setup required for unmodifiableSet tests
    }

    @Test
    public void testUnmodifiableSetWithNull() {
        assertNull(SetUtils.unmodifiableSet((Object[]) null));
    }

    @Test
    public void testUnmodifiableSetWithEmptyArray() {
        Set<Object> set = SetUtils.unmodifiableSet();
        assertTrue(set.isEmpty());
    }

    @Test
    public void testUnmodifiableSetWithNonEmptyArray() {
        Set<String> set = SetUtils.unmodifiableSet("a", "b", "c");
        assertEquals(3, set.size());
        assertTrue(set.contains("a"));
        assertTrue(set.contains("b"));
        assertTrue(set.contains("c"));
    }

    @Test
    public void testUnmodifiableSetWithDuplicates() {
        Set<String> set = SetUtils.unmodifiableSet("a", "b", "a", "c", "b");
        assertEquals(3, set.size());
        assertTrue(set.contains("a"));
        assertTrue(set.contains("b"));
        assertTrue(set.contains("c"));
    }

    @Test
    public void testUnmodifiableSetWithMixedTypes() {
        Set<Object> set = SetUtils.unmodifiableSet("a", 1, 2.0, true);
        assertEquals(4, set.size());
        assertTrue(set.contains("a"));
        assertTrue(set.contains(1));
        assertTrue(set.contains(2.0));
        assertTrue(set.contains(true));
    }
}
