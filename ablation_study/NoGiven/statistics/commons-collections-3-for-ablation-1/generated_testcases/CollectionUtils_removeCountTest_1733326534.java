
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionUtils_removeCountTest {

    @Test
    public void testRemoveCount_NormalCase() {
        Collection<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Collection<Integer> result = CollectionUtils.removeCount(input, 1, 2);

        assertEquals(Arrays.asList(2, 3), result);
        assertEquals(Arrays.asList(1, 4, 5), input);
    }

    @Test
    public void testRemoveCount_StartIndexNegative() {
        Collection<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.removeCount(input, -1, 2));
    }

    @Test
    public void testRemoveCount_CountNegative() {
        Collection<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.removeCount(input, 1, -1));
    }

    @Test
    public void testRemoveCount_StartIndexPlusCountExceedsSize() {
        Collection<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.removeCount(input, 3, 3));
    }

    @Test
    public void testRemoveCount_InputNull() {
        assertThrows(NullPointerException.class, () -> CollectionUtils.removeCount(null, 1, 2));
    }
}
