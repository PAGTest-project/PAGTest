
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EqualPredicate_equalPredicateTest {

    @Test
    void testEqualPredicateWithNonNullObject() {
        String input = "test";
        Predicate<String> predicate = EqualPredicate.equalPredicate(input);
        assertTrue(predicate.test(input));
        assertFalse(predicate.test("different"));
    }

    @Test
    void testEqualPredicateWithNullObject() {
        Predicate<String> predicate = EqualPredicate.equalPredicate(null);
        assertTrue(predicate.test(null));
        assertFalse(predicate.test("notNull"));
    }
}
