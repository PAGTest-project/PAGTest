
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

    private List<String> list1;
    private List<String> list2;
    private List<String> list3;
    private List<Iterator<String>> iteratorList;

    @BeforeEach
    public void setUp() {
        list1 = new ArrayList<>();
        list1.add("One");
        list1.add("Two");
        list1.add("Three");
        list2 = new ArrayList<>();
        list2.add("Four");
        list3 = new ArrayList<>();
        list3.add("Five");
        list3.add("Six");
        iteratorList = new ArrayList<>();
        iteratorList.add(list1.iterator());
        iteratorList.add(list2.iterator());
        iteratorList.add(list3.iterator());
    }

    @Test
    public void testRemoveWithoutNext() {
        final Iterator<Object> it = new ObjectGraphIterator<>(iteratorList.iterator());

        assertThrows(IllegalStateException.class, () -> it.remove());
    }

    @Test
    public void testRemoveAfterNext() {
        final Iterator<Object> it = new ObjectGraphIterator<>(iteratorList.iterator());

        it.next(); // Move to the first element
        it.remove(); // Remove the first element

        // Verify that the first element is removed
        assertFalse(list1.contains("One"));
    }

    @Test
    public void testRemoveTwice() {
        final Iterator<Object> it = new ObjectGraphIterator<>(iteratorList.iterator());

        it.next(); // Move to the first element
        it.remove(); // Remove the first element

        assertThrows(IllegalStateException.class, () -> it.remove());
    }

    @Test
    public void testRemoveAfterEndOfIteration() {
        final Iterator<Object> it = new ObjectGraphIterator<>(iteratorList.iterator());

        // Iterate through all elements
        while (it.hasNext()) {
            it.next();
        }

        // Ensure the iterator is at the end before calling remove
        assertFalse(it.hasNext());

        assertThrows(IllegalStateException.class, () -> it.remove());
    }
}
