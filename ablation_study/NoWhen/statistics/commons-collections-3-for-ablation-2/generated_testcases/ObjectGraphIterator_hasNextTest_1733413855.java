
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjectGraphIterator_hasNextTest {
    private List<String> list1;
    private List<String> list2;
    private List<String> list3;

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
    }

    @Test
    public void testHasNextWithRootIterator() {
        final Iterator<String> rootIterator = list1.iterator();
        final ObjectGraphIterator<String> iterator = new ObjectGraphIterator<>(rootIterator);

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testHasNextWithRootObject() {
        final ObjectGraphIterator<String> iterator = new ObjectGraphIterator<>(list1);

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testHasNextWithEmptyIterator() {
        final Iterator<String> emptyIterator = list2.iterator();
        final ObjectGraphIterator<String> iterator = new ObjectGraphIterator<>(emptyIterator);

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testHasNextWithNullRoot() {
        final ObjectGraphIterator<String> iterator = new ObjectGraphIterator<>(null);

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testHasNextWithTransformer() {
        final Transformer<String, String> transformer = input -> input + " Transformed";
        final ObjectGraphIterator<String> iterator = new ObjectGraphIterator<>(list1, transformer);

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testHasNextWithNestedIterators() {
        final List<Iterator<String>> iteratorList = new ArrayList<>();
        iteratorList.add(list1.iterator());
        iteratorList.add(list2.iterator());
        iteratorList.add(list3.iterator());
        final ObjectGraphIterator<String> iterator = new ObjectGraphIterator<>(iteratorList.iterator());

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }
}
