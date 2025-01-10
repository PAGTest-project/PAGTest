
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

    private ObjectGraphIterator<String> iterator;

    @BeforeEach
    public void setUp() {
        iterator = new ObjectGraphIterator<>(null);
    }

    @Test
    public void testHasNext_NoElements() {
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testHasNext_WithElements() {
        List<String> list = new ArrayList<>();
        list.add("Element1");
        list.add("Element2");
        iterator = new ObjectGraphIterator<>(list.iterator());
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testHasNext_AfterNext() {
        List<String> list = new ArrayList<>();
        list.add("Element1");
        list.add("Element2");
        iterator = new ObjectGraphIterator<>(list.iterator());
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testHasNext_WithTransformer() {
        Transformer<String, String> transformer = input -> input + "_transformed";
        List<String> list = new ArrayList<>();
        list.add("Element1");
        list.add("Element2");
        iterator = new ObjectGraphIterator<>(list.iterator(), transformer);
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testHasNext_NoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    @Test
    public void testHasNext_RemoveNotSupported() {
        List<String> list = new ArrayList<>();
        list.add("Element1");
        iterator = new ObjectGraphIterator<>(list.iterator());
        iterator.next();
        assertThrows(UnsupportedOperationException.class, () -> {
            iterator.remove();
        });
    }
}
