
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

    private ObjectGraphIterator<Object> iterator;

    @BeforeEach
    public void setUp() {
        iterator = new ObjectGraphIterator<>(null, null);
    }

    @Test
    public void testHasNext_RootNull() {
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testHasNext_RootNotNull() {
        List<Object> list = new ArrayList<>();
        list.add("element1");
        list.add("element2");
        iterator = new ObjectGraphIterator<>(list.iterator(), null);

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testHasNext_WithTransformer() {
        Transformer<Object, Object> transformer = obj -> obj + "_transformed";
        List<Object> list = new ArrayList<>();
        list.add("element1");
        list.add("element2");
        iterator = new ObjectGraphIterator<>(list.iterator(), transformer);

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testHasNext_NestedIterators() {
        List<Object> list1 = new ArrayList<>();
        list1.add("element1");
        List<Object> list2 = new ArrayList<>();
        list2.add("element2");
        List<Iterator<?>> nestedList = new ArrayList<>();
        nestedList.add(list1.iterator());
        nestedList.add(list2.iterator());
        iterator = new ObjectGraphIterator<>(nestedList.iterator());

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testHasNext_NoSuchElementException() {
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }
}
