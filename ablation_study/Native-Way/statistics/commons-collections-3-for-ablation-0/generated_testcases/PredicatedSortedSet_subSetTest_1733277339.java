
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PredicatedSortedSet_subSetTest {

    private PredicatedSortedSet<Integer> predicatedSortedSet;
    private SortedSet<Integer> testSet;
    private Predicate<Integer> truePredicate;

    @BeforeEach
    public void setUp() {
        testSet = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5));
        truePredicate = TruePredicate.truePredicate();
        predicatedSortedSet = new PredicatedSortedSet<>(testSet, truePredicate);
    }

    @Test
    public void testSubSetValidRange() {
        SortedSet<Integer> subSet = predicatedSortedSet.subSet(2, 4);
        assertNotNull(subSet, "SubSet should not be null");
        assertEquals(2, subSet.size(), "SubSet size should be 2");
        assertTrue(subSet.contains(2), "SubSet should contain 2");
        assertTrue(subSet.contains(3), "SubSet should contain 3");
    }

    @Test
    public void testSubSetInvalidRange() {
        SortedSet<Integer> subSet = predicatedSortedSet.subSet(4, 2);
        assertNotNull(subSet, "SubSet should not be null");
        assertTrue(subSet.isEmpty(), "SubSet should be empty");
    }

    @Test
    public void testSubSetWithPredicate() {
        Predicate<Integer> evenPredicate = new Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer object) {
                return object % 2 == 0;
            }
        };
        PredicatedSortedSet<Integer> predicatedSet = new PredicatedSortedSet<>(testSet, evenPredicate);
        SortedSet<Integer> subSet = predicatedSet.subSet(1, 5);
        assertNotNull(subSet, "SubSet should not be null");
        assertEquals(2, subSet.size(), "SubSet size should be 2");
        assertTrue(subSet.contains(2), "SubSet should contain 2");
        assertTrue(subSet.contains(4), "SubSet should contain 4");
    }
}
