
package org.apache.commons.collections4.functors;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class ComparatorPredicate_testTest {

    private static final Comparator<Integer> INT_COMPARATOR = Integer::compare;

    @Test
    void testEqualCriterion() {
        ComparatorPredicate<Integer> predicate = new ComparatorPredicate<>(5, INT_COMPARATOR, ComparatorPredicate.Criterion.EQUAL);
        assertTrue(predicate.test(5));
        assertFalse(predicate.test(4));
        assertFalse(predicate.test(6));
    }

    @Test
    void testGreaterCriterion() {
        ComparatorPredicate<Integer> predicate = new ComparatorPredicate<>(5, INT_COMPARATOR, ComparatorPredicate.Criterion.GREATER);
        assertFalse(predicate.test(5));
        assertFalse(predicate.test(4));
        assertTrue(predicate.test(6));
    }

    @Test
    void testLessCriterion() {
        ComparatorPredicate<Integer> predicate = new ComparatorPredicate<>(5, INT_COMPARATOR, ComparatorPredicate.Criterion.LESS);
        assertFalse(predicate.test(5));
        assertTrue(predicate.test(4));
        assertFalse(predicate.test(6));
    }

    @Test
    void testGreaterOrEqualCriterion() {
        ComparatorPredicate<Integer> predicate = new ComparatorPredicate<>(5, INT_COMPARATOR, ComparatorPredicate.Criterion.GREATER_OR_EQUAL);
        assertTrue(predicate.test(5));
        assertFalse(predicate.test(4));
        assertTrue(predicate.test(6));
    }

    @Test
    void testLessOrEqualCriterion() {
        ComparatorPredicate<Integer> predicate = new ComparatorPredicate<>(5, INT_COMPARATOR, ComparatorPredicate.Criterion.LESS_OR_EQUAL);
        assertTrue(predicate.test(5));
        assertTrue(predicate.test(4));
        assertFalse(predicate.test(6));
    }

    @Test
    void testInvalidCriterion() {
        ComparatorPredicate<Integer> predicate = new ComparatorPredicate<>(5, INT_COMPARATOR, null);
        assertThrows(IllegalStateException.class, () -> predicate.test(5));
    }
}
