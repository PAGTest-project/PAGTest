
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtils_removeRangeTest {

    @Test
    void testRemoveRange_SuccessfulRemoval() {
        Collection<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        Collection<Integer> expected = Arrays.asList(1, 5);

        Collection<Integer> result = CollectionUtils.removeRange(input, 1, 4);

        assertEquals(expected, result);
    }

    @Test
    void testRemoveRange_NullInput() {
        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.removeRange(null, 1, 2);
        });
    }

    @Test
    void testRemoveRange_EndIndexLessThanStartIndex() {
        Collection<Integer> input = Arrays.asList(1, 2, 3, 4, 5);

        assertThrows(IllegalArgumentException.class, () -> {
            CollectionUtils.removeRange(input, 3, 2);
        });
    }

    @Test
    void testRemoveRange_EndIndexGreaterThanSize() {
        Collection<Integer> input = Arrays.asList(1, 2, 3, 4, 5);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            CollectionUtils.removeRange(input, 1, 6);
        });
    }
}
