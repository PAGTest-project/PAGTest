
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoundedIterator_hasNextTest {

    private List<String> testList;

    @BeforeEach
    public void setUp() {
        testList = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
    }

    @Test
    public void testHasNextWithinBounds() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 1, 5);
        assertTrue(iter.hasNext());
    }

    @Test
    public void testHasNextOutOfBounds() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 1, 5);
        for (int i = 0; i < 5; i++) {
            iter.next();
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextWithZeroMax() {
        Iterator<String> iter = new BoundedIterator<>(testList.iterator(), 1, 0);
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextWithNegativeOffset() {
        assertThrows(IllegalArgumentException.class, () -> new BoundedIterator<>(testList.iterator(), -1, 5));
    }

    @Test
    public void testHasNextWithNegativeMax() {
        assertThrows(IllegalArgumentException.class, () -> new BoundedIterator<>(testList.iterator(), 1, -1));
    }
}
