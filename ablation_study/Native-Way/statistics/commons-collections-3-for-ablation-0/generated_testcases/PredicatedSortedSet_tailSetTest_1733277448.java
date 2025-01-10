
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;

import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PredicatedSortedSet_tailSetTest {

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
    public void testTailSetValid() {
        SortedSet<Integer> result = predicatedSortedSet.tailSet(3);
        assertNotNull(result, "tailSet should not return null");
        assertEquals(3, result.size(), "tailSet should contain 3 elements");
        assertTrue(result.contains(3), "tailSet should contain element 3");
        assertTrue(result.contains(4), "tailSet should contain element 4");
        assertTrue(result.contains(5), "tailSet should contain element 5");
    }

    @Test
    public void testTailSetInvalid() {
        SortedSet<Integer> result = predicatedSortedSet.tailSet(6);
        assertNotNull(result, "tailSet should not return null");
        assertTrue(result.isEmpty(), "tailSet should be empty for invalid fromElement");
    }

    @Test
    public void testTailSetNullPredicate() {
        assertThrows(NullPointerException.class, () -> {
            new PredicatedSortedSet<>(testSet, null).tailSet(3);
        }, "Null predicate should throw NullPointerException");
    }

    @Test
    public void testTailSetNullSet() {
        assertThrows(NullPointerException.class, () -> {
            new PredicatedSortedSet<>(null, truePredicate).tailSet(3);
        }, "Null set should throw NullPointerException");
    }
}
