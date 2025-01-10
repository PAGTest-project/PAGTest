
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_iteratorTest {

    private CompositeCollection<String> compositeCollection;
    private List<String> list1;
    private List<String> list2;

    @BeforeEach
    public void setUp() {
        compositeCollection = new CompositeCollection<>();
        list1 = new ArrayList<>(Arrays.asList("A", "B", "C"));
        list2 = new ArrayList<>(Arrays.asList("D", "E", "F"));
    }

    @Test
    public void testIteratorWithEmptyCompositeCollection() {
        Iterator<String> iterator = compositeCollection.iterator();
        assertTrue(iterator instanceof EmptyIterator);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorWithNonEmptyCompositeCollection() {
        compositeCollection.addComposited(list1, list2);
        Iterator<String> iterator = compositeCollection.iterator();
        assertTrue(iterator instanceof IteratorChain);

        List<String> expected = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F"));
        List<String> actual = new ArrayList<>();
        iterator.forEachRemaining(actual::add);

        assertEquals(expected, actual);
    }
}
