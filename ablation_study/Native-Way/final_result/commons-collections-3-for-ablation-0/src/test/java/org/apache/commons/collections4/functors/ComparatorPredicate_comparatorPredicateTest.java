
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparatorPredicate_comparatorPredicateTest {

    @Test
    public void testComparatorPredicate_WithNonNullComparatorAndCriterion() {
        Comparator<Integer> comparator = Integer::compare;
        Predicate<Integer> predicate = ComparatorPredicate.comparatorPredicate(5, comparator, ComparatorPredicate.Criterion.EQUAL);
        assertTrue(predicate.test(5));
    }

    @Test
    public void testComparatorPredicate_WithNullComparator() {
        assertThrows(NullPointerException.class, () -> {
            ComparatorPredicate.comparatorPredicate(5, null, ComparatorPredicate.Criterion.EQUAL);
        });
    }

    @Test
    public void testComparatorPredicate_WithNullCriterion() {
        Comparator<Integer> comparator = Integer::compare;
        assertThrows(NullPointerException.class, () -> {
            ComparatorPredicate.comparatorPredicate(5, comparator, null);
        });
    }
}
