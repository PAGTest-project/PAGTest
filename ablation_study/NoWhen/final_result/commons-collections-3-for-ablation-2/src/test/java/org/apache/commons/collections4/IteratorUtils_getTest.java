
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

public class IteratorUtils_getTest {

    @Test
    public void testGetElementAtIndex() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3, 4, 5).iterator();
        assertEquals(3, IteratorUtils.get(iterator, 2));
    }

    @Test
    public void testGetElementAtIndexOutOfBounds() {
        Iterator<Integer> iterator = Arrays.asList(1, 2, 3).iterator();
        assertThrows(IndexOutOfBoundsException.class, () -> IteratorUtils.get(iterator, 3));
    }
}
