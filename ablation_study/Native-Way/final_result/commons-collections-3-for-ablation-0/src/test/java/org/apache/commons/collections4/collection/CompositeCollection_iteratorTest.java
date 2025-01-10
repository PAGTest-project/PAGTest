
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_iteratorTest {
    private CompositeCollection<String> c;
    private Collection<String> one;
    private Collection<String> two;

    @BeforeEach
    public void setUp() {
        c = new CompositeCollection<>();
        one = new HashSet<>();
        two = new HashSet<>();
    }

    @Test
    public void testIteratorWithEmptyCompositeCollection() {
        Iterator<String> iterator = c.iterator();
        assertTrue(iterator instanceof EmptyIterator);
    }

    @Test
    public void testIteratorWithNonEmptyCompositeCollection() {
        one.add("1");
        two.add("2");
        c.addComposited(one, two);

        Iterator<String> iterator = c.iterator();
        assertTrue(iterator instanceof IteratorChain);

        List<String> result = new ArrayList<>();
        iterator.forEachRemaining(result::add);

        assertEquals(2, result.size());
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
    }

    @Test
    public void testIteratorWithOneCollection() {
        one.add("1");
        c.addComposited(one);

        Iterator<String> iterator = c.iterator();
        assertTrue(iterator instanceof IteratorChain);

        List<String> result = new ArrayList<>();
        iterator.forEachRemaining(result::add);

        assertEquals(1, result.size());
        assertTrue(result.contains("1"));
    }

    @Test
    public void testIteratorWithMultipleCollections() {
        Collection<String> three = new HashSet<>();
        three.add("3");

        one.add("1");
        two.add("2");
        c.addComposited(one, two, three);

        Iterator<String> iterator = c.iterator();
        assertTrue(iterator instanceof IteratorChain);

        List<String> result = new ArrayList<>();
        iterator.forEachRemaining(result::add);

        assertEquals(3, result.size());
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("3"));
    }
}
