
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
        testList = Arrays.asList("A", "B", "C", "D", "E");
    }

    @Test
    public void testRemoveBeforeNext() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 2, 2);
        assertThrows(IllegalStateException.class, () -> iter.remove());
    }

    @Test
    public void testRemoveAfterNext() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 2, 2);
        iter.next(); // Move to the first element within bounds
        assertThrows(UnsupportedOperationException.class, () -> iter.remove());
    }
}
