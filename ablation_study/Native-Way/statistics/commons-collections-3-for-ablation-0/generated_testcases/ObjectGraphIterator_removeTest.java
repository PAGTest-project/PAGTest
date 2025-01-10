
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjectGraphIterator_removeTest {

    private ObjectGraphIterator<String> iterator;
    private List<String> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        iterator = new ObjectGraphIterator<>(list.iterator());
    }

    @Test
    public void testRemove_Success() {
        iterator.next(); // Move to the first element
        iterator.remove(); // Remove the first element
        assertFalse(list.contains("A"));
    }

    @Test
    public void testRemove_WithoutNextCall() {
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }

    @Test
    public void testRemove_AfterAlreadyRemoved() {
        iterator.next(); // Move to the first element
        iterator.remove(); // Remove the first element
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }

    @Test
    public void testRemove_AfterIterationEnd() {
        while (iterator.hasNext()) {
            iterator.next();
        }
        iterator.next(); // Move past the end
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }

    @Test
    public void testRemove_AfterNoSuchElementException() {
        while (iterator.hasNext()) {
            iterator.next();
        }
        assertThrows(NoSuchElementException.class, () -> iterator.next());
        iterator.next(); // Move past the end
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }
}
