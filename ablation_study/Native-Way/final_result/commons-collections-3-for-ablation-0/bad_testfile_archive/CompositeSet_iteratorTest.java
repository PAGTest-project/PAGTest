
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeSet_iteratorTest {

    private CompositeSet<String> compositeSet;

    @BeforeEach
    public void setUp() {
        compositeSet = new CompositeSet<>();
    }

    @Test
    public void testIteratorWithEmptyCompositeSet() {
        Iterator<String> iterator = compositeSet.iterator();
        assertTrue(iterator instanceof EmptyIterator);
    }

    @Test
    public void testIteratorWithNonEmptyCompositeSet() {
        Set<String> set1 = new HashSet<>();
        set1.add("A");
        set1.add("B");
        compositeSet.addComposited(set1);

        Set<String> set2 = new HashSet<>();
        set2.add("C");
        compositeSet.addComposited(set2);

        Iterator<String> iterator = compositeSet.iterator();
        assertTrue(iterator instanceof IteratorChain);

        Set<String> resultSet = new HashSet<>();
        iterator.forEachRemaining(resultSet::add);

        assertEquals(3, resultSet.size());
        assertTrue(resultSet.contains("A"));
        assertTrue(resultSet.contains("B"));
        assertTrue(resultSet.contains("C"));
    }

    @Test
    public void testIteratorWithEmptySetInCompositeSet() {
        Set<String> set1 = new HashSet<>();
        set1.add("A");
        compositeSet.addComposited(set1);

        Set<String> set2 = new HashSet<>();
        compositeSet.addComposited(set2);

        Iterator<String> iterator = compositeSet.iterator();
        assertTrue(iterator instanceof IteratorChain);

        Set<String> resultSet = new HashSet<>();
        iterator.forEachRemaining(resultSet::add);

        assertEquals(1, resultSet.size());
        assertTrue(resultSet.contains("A"));
    }

    @Test
    public void testIteratorWithAllEmptySetsInCompositeSet() {
        Set<String> set1 = new HashSet<>();
        compositeSet.addComposited(set1);

        Set<String> set2 = new HashSet<>();
        compositeSet.addComposited(set2);

        Iterator<String> iterator = compositeSet.iterator();
        assertTrue(iterator instanceof EmptyIterator);
    }
}
