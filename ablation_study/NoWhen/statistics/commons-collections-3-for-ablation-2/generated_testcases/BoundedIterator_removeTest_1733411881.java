
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
        testList = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
    }

    @Test
    public void testRemoveSuccess() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 1, 10);
        iter.next(); // Move to the first element in the bounded range
        iter.remove(); // Remove the element
        assertFalse(testList.contains("b")); // Verify the element was removed
    }

    @Test
    public void testRemoveBeforeNext() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 1, 10);
        assertThrows(IllegalStateException.class, () -> iter.remove());
    }
}
