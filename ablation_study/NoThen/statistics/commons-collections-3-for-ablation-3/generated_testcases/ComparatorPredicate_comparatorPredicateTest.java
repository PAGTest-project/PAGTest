
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparatorPredicate_comparatorPredicateTest {

    @Test
    public void testComparatorPredicate_WithNonNullComparatorAndCriterion() {
        // Given
        Integer object = 10;
        Comparator<Integer> comparator = Integer::compareTo;
        ComparatorPredicate.Criterion criterion = ComparatorPredicate.Criterion.EQUAL;

        // When
        Predicate<Integer> predicate = ComparatorPredicate.comparatorPredicate(object, comparator, criterion);

        // Then
        assertTrue(predicate.test(10));
    }

    @Test
    public void testComparatorPredicate_WithNullComparator() {
        // Given
        Integer object = 10;
        Comparator<Integer> comparator = null;
        ComparatorPredicate.Criterion criterion = ComparatorPredicate.Criterion.EQUAL;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            ComparatorPredicate.comparatorPredicate(object, comparator, criterion);
        });
    }

    @Test
    public void testComparatorPredicate_WithNullCriterion() {
        // Given
        Integer object = 10;
        Comparator<Integer> comparator = Integer::compareTo;
        ComparatorPredicate.Criterion criterion = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> {
            ComparatorPredicate.comparatorPredicate(object, comparator, criterion);
        });
    }
}
