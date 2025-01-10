
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SetUtils_hashSetTest {

    @Test
    public void testHashSetWithNullInput() {
        // Given
        Object[] items = null;

        // When
        HashSet<Object> result = SetUtils.hashSet(items);

        // Then
        assertNull(result);
    }

    @Test
    public void testHashSetWithNonNullInput() {
        // Given
        Integer[] items = {1, 2, 3};

        // When
        HashSet<Integer> result = SetUtils.hashSet(items);

        // Then
        assertEquals(3, result.size());
        assertEquals(new HashSet<>(Arrays.asList(items)), result);
    }
}
