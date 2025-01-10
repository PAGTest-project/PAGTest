
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IterableUtils_getTest {

    @Test
    public void testGetFromList() {
        List<String> list = Arrays.asList("a", "b", "c");
        assertEquals("b", IterableUtils.get(list, 1));
    }

    @Test
    public void testGetFromNonListIterable() {
        Iterable<String> iterable = Arrays.asList("a", "b", "c");
        assertEquals("b", IterableUtils.get(iterable, 1));
    }

    @Test
    public void testGetWithInvalidIndex() {
        List<String> list = Arrays.asList("a", "b", "c");
        assertThrows(IndexOutOfBoundsException.class, () -> IterableUtils.get(list, 3));
    }
}
