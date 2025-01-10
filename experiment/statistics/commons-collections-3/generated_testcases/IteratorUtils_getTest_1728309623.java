
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Iterator;

public class IteratorUtils_getTest {

    @Test
    public void testGetElementAtIndex() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3, 4, 5).iterator();
        assertEquals(Integer.valueOf(3), IteratorUtils.get(iterator, 2));
    }

    @Test
    public void testGetElementAtIndexOutOfBounds() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3).iterator();
        assertThrows(IndexOutOfBoundsException.class, () -> IteratorUtils.get(iterator, 3));
    }

    @Test
    public void testGetElementAtIndexEmptyIterator() {
        Iterator<Integer> iterator = Arrays.asList().iterator();
        assertThrows(IndexOutOfBoundsException.class, () -> IteratorUtils.get(iterator, 0));
    }
}
