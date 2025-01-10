
package org.apache.commons.collections4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorUtils_toListTest {

    private Iterator<String> iterator;

    @BeforeEach
    public void setUp() {
        List<String> list = Arrays.asList("a", "b", "c");
        iterator = list.iterator();
    }

    @Test
    public void testToListWithValidEstimatedSize() {
        List<String> result = IteratorUtils.toList(iterator, 3);
        assertEquals(Arrays.asList("a", "b", "c"), result);
    }

    @Test
    public void testToListWithInvalidEstimatedSize() {
        assertThrows(IllegalArgumentException.class, () -> {
            IteratorUtils.toList(iterator, 0);
        });
    }

    @Test
    public void testToListWithNullIterator() {
        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.toList(null, 3);
        });
    }
}
