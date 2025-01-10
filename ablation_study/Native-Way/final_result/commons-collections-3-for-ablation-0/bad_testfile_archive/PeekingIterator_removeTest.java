
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PeekingIterator_removeTest {

    private PeekingIterator<String> peekingIterator;
    private List<String> testList;

    @BeforeEach
    public void setUp() {
        testList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        peekingIterator = new PeekingIterator<>(testList.iterator());
    }

    @Test
    public void testRemoveWithoutPeekOrElement() {
        peekingIterator.next(); // Move to the first element
        peekingIterator.remove(); // Remove the first element
        assertEquals(Arrays.asList("B", "C"), testList);
    }

    @Test
    public void testRemoveAfterPeek() {
        peekingIterator.peek(); // Peek at the first element
        assertThrows(IllegalStateException.class, () -> {
            peekingIterator.remove(); // Should throw IllegalStateException
        });
    }

    @Test
    public void testRemoveAfterElement() {
        peekingIterator.element(); // Element at the first element
        assertThrows(IllegalStateException.class, () -> {
            peekingIterator.remove(); // Should throw IllegalStateException
        });
    }

    @Test
    public void testRemoveOnEmptyIterator() {
        Iterator<String> emptyIterator = new ArrayList<String>().iterator();
        PeekingIterator<String> emptyPeekingIterator = new PeekingIterator<>(emptyIterator);
        assertThrows(IllegalStateException.class, () -> {
            emptyPeekingIterator.remove(); // Should throw IllegalStateException
        });
    }

    @Test
    public void testRemoveAfterExhausted() {
        while (peekingIterator.hasNext()) {
            peekingIterator.next();
        }
        peekingIterator.peek(); // Add this line to set slotFilled to true
        peekingIterator.next(); // Move to the end of the iterator
        assertThrows(IllegalStateException.class, () -> {
            peekingIterator.remove(); // Should throw IllegalStateException
        });
    }
}
