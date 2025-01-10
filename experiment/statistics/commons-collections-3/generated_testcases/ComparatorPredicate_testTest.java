
package org.apache.commons.collections4.functors;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class ComparatorPredicate_testTest {

    @Test
    void testComparatorPredicate() {
        Comparator<Integer> comparator = Integer::compare;
        ComparatorPredicate<Integer> predicate = new ComparatorPredicate<>(5, comparator, ComparatorPredicate.Criterion.EQUAL);

        // Test EQUAL criterion
        assertTrue(predicate.test(5));
        assertFalse(predicate.test(4));
        assertFalse(predicate.test(6));

        // Test GREATER criterion
        predicate = new ComparatorPredicate<>(5, comparator, ComparatorPredicate.Criterion.GREATER);
        assertFalse(predicate.test(5));
        assertFalse(predicate.test(4));
        assertTrue(predicate.test(6));

        // Test LESS criterion
        predicate = new ComparatorPredicate<>(5, comparator, ComparatorPredicate.Criterion.LESS);
        assertFalse(predicate.test(5));
        assertTrue(predicate.test(4));
        assertFalse(predicate.test(6));

        // Test GREATER_OR_EQUAL criterion
        predicate = new ComparatorPredicate<>(5, comparator, ComparatorPredicate.Criterion.GREATER_OR_EQUAL);
        assertTrue(predicate.test(5));
        assertFalse(predicate.test(4));
        assertTrue(predicate.test(6));

        // Test LESS_OR_EQUAL criterion
        predicate = new ComparatorPredicate<>(5, comparator, ComparatorPredicate.Criterion.LESS_OR_EQUAL);
        assertTrue(predicate.test(5));
        assertTrue(predicate.test(4));
        assertFalse(predicate.test(6));

        // Test invalid criterion (should throw NullPointerException)
        ComparatorPredicate.Criterion invalidCriterion = null;
        assertThrows(NullPointerException.class, () -> new ComparatorPredicate<>(5, comparator, invalidCriterion));
    }
}
