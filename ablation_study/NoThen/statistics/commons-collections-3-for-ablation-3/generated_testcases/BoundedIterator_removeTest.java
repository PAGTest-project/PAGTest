
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoundedIterator_removeTest {
    private List<String> testList;

    @BeforeEach
    public void setUp() {
        testList = Arrays.asList("a", "b", "c", "d", "e", "f");
    }

    @Test
    public void testRemoveSuccess() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 2, 4);
        iter.next(); // Move to "c"
        iter.next(); // Move to "d"
        iter.remove(); // Remove "d"
        assertEquals("e", iter.next()); // Ensure "e" is the next element
    }

    @Test
    public void testRemoveBeforeNext() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 2, 4);
        assertThrows(IllegalStateException.class, () -> iter.remove(),
                "Expected IllegalStateException.");
    }

    @Test
    public void testRemoveAfterBounds() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 2, 4);
        iter.next(); // Move to "c"
        iter.next(); // Move to "d"
        iter.next(); // Move to "e"
        iter.next(); // Move to "f"
        assertThrows(IllegalStateException.class, () -> iter.remove(),
                "Expected IllegalStateException.");
    }
}
