
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PeekingIterator_removeTest {

    private List<String> testList;
    private static final String[] testArray = {"a", "b", "c"};

    @SuppressWarnings("unchecked")
    @BeforeEach
    protected void setUp() throws Exception {
        testList = new ArrayList<>(Arrays.asList((String[]) testArray));
    }

    @Test
    public void testRemoveWithoutPeekOrElement() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        it.next(); // Move to the first element
        it.remove(); // Remove the first element
        assertEquals(2, testList.size());
        assertEquals("b", testList.get(0));
        assertEquals("c", testList.get(1));
    }

    @Test
    public void testRemoveAfterPeek() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        it.peek(); // Peek the first element
        assertThrows(IllegalStateException.class, () -> it.remove());
    }

    @Test
    public void testRemoveAfterElement() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        it.element(); // Element the first element
        assertThrows(IllegalStateException.class, () -> it.remove());
    }

    @Test
    public void testRemoveOnEmptyIterator() {
        PeekingIterator<String> it = new PeekingIterator<>(new ArrayList<String>().iterator());
        assertThrows(IllegalStateException.class, () -> it.remove());
    }
}
