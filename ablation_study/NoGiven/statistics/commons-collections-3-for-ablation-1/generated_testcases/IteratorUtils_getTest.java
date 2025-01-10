
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorUtils_getTest {

    @Test
    public void testGet_ValidIndex() {
        Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        String result = IteratorUtils.get(iterator, 1);
        assertEquals("b", result);
    }

    @Test
    public void testGet_IndexOutOfBounds() {
        Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        assertThrows(IndexOutOfBoundsException.class, () -> IteratorUtils.get(iterator, 3));
    }
}
