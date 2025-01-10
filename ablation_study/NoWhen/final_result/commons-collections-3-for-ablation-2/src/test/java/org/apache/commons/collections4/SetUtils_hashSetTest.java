
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetUtils_hashSetTest {

    private Set<Integer> setA;
    private Set<Integer> setB;

    @BeforeEach
    public void setUp() {
        setA = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        setB = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7));
    }

    @Test
    public void testHashSetWithNull() {
        Set<Integer> result = SetUtils.hashSet((Integer[]) null);
        assertNull(result);
    }

    @Test
    public void testHashSetWithItems() {
        Set<Integer> result = SetUtils.hashSet(1, 2, 3);
        assertEquals(3, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    @Test
    public void testHashSetWithEmptyItems() {
        Set<Integer> result = SetUtils.hashSet();
        assertEquals(0, result.size());
    }
}
