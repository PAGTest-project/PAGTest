
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testRemoveAfterNext() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        assertTrue(it.hasNext());
        assertEquals("a", it.next());
        it.remove();
        assertEquals(Arrays.asList("b", "c"), testList);
    }

    @Test
    public void testRemoveWithoutPeekOrElement() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        assertTrue(it.hasNext());
        assertThrows(IllegalStateException.class, () -> it.remove());
    }

    @Test
    public void testRemoveAfterPeek() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        assertTrue(it.hasNext());
        assertEquals("a", it.peek());
        assertThrows(IllegalStateException.class, () -> it.remove());
    }

    @Test
    public void testRemoveAfterElement() {
        PeekingIterator<String> it = new PeekingIterator<>(testList.iterator());
        assertTrue(it.hasNext());
        assertEquals("a", it.element());
        assertThrows(IllegalStateException.class, () -> it.remove());
    }
}
