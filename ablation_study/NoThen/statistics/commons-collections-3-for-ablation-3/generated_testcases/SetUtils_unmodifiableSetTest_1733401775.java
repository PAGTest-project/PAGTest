
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import org.junit.jupiter.api.Test;

public class SetUtils_unmodifiableSetTest {

    @Test
    public void testUnmodifiableSetWithNullInput() {
        Set<Integer> result = SetUtils.unmodifiableSet((Integer[]) null);
        assertNull(result);
    }

    @Test
    public void testUnmodifiableSetWithEmptyInput() {
        Set<Integer> result = SetUtils.unmodifiableSet();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testUnmodifiableSetWithNonEmptyInput() {
        Set<Integer> result = SetUtils.unmodifiableSet(1, 2, 3);
        assertEquals(3, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    @Test
    public void testUnmodifiableSetWithDuplicates() {
        Set<Integer> result = SetUtils.unmodifiableSet(1, 2, 2, 3, 3, 3);
        assertEquals(3, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    @Test
    public void testUnmodifiableSetWithSynchronizedSet() {
        Set<Integer> synchronizedSet = SetUtils.synchronizedSet(SetUtils.hashSet(1, 2, 3));
        Set<Integer> result = SetUtils.unmodifiableSet(synchronizedSet.toArray(new Integer[0]));
        assertEquals(3, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    @Test
    public void testUnmodifiableSetWithPredicatedSet() {
        Set<Integer> predicatedSet = SetUtils.predicatedSet(SetUtils.hashSet(1, 2, 3), e -> e > 0);
        Set<Integer> result = SetUtils.unmodifiableSet(predicatedSet.toArray(new Integer[0]));
        assertEquals(3, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    @Test
    public void testUnmodifiableSetWithInvalidInput() {
        assertThrows(NullPointerException.class, () -> {
            SetUtils.unmodifiableSet(new Integer[] { null });
        });
    }
}
