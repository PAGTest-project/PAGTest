
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EqualPredicate_equalPredicateTest {

    @Test
    void testEqualPredicateWithNonNullObject() {
        String input = "test";
        Predicate<String> predicate = EqualPredicate.equalPredicate(input);
        assertNotNull(predicate);
        assertTrue(predicate.test(input));
        assertFalse(predicate.test("other"));
    }

    @Test
    void testEqualPredicateWithNullObject() {
        Predicate<String> predicate = EqualPredicate.equalPredicate(null);
        assertNotNull(predicate);
        assertTrue(predicate.test(null));
        assertFalse(predicate.test("nonNull"));
    }
}
