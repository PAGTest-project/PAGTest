
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        final Iterator<Object> it = new ObjectGraphIterator<>(iteratorList.iterator(), null);
        assertThrows(IllegalStateException.class, it::remove);
    }

    @Test
    public void testRemoveAfterNext() {
        final Iterator<Object> it = new ObjectGraphIterator<>(iteratorList.iterator(), null);
        assertTrue(it.hasNext());
        it.next();
        it.remove();
    }
}
