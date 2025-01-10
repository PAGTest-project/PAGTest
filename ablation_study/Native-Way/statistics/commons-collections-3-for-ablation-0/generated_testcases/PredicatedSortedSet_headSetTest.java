
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Arrays;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PredicatedSortedSet_headSetTest {

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
    public void testHeadSetValid() {
        SortedSet<Integer> headSet = predicatedSortedSet.headSet(3);
        assertNotNull(headSet, "headSet should not be null");
        assertEquals(2, headSet.size(), "headSet should contain 2 elements");
        assertTrue(headSet.contains(1), "headSet should contain element 1");
        assertTrue(headSet.contains(2), "headSet should contain element 2");
    }

    @Test
    public void testHeadSetInvalid() {
        SortedSet<Integer> headSet = predicatedSortedSet.headSet(0);
        assertNotNull(headSet, "headSet should not be null");
        assertTrue(headSet.isEmpty(), "headSet should be empty");
    }

    @Test
    public void testHeadSetBoundary() {
        SortedSet<Integer> headSet = predicatedSortedSet.headSet(1);
        assertNotNull(headSet, "headSet should not be null");
        assertTrue(headSet.isEmpty(), "headSet should be empty");
    }
}
