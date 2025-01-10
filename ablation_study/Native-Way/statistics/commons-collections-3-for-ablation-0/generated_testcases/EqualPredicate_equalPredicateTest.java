
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EqualPredicate_equalPredicateTest {

    @Test
    public void testEqualPredicateWithNonNullObject() {
        String input = "test";
        Predicate<String> predicate = EqualPredicate.equalPredicate(input);
        assertNotNull(predicate);
        assertTrue(predicate.test(input));
    }

    @Test
    public void testEqualPredicateWithNullObject() {
        Predicate<String> predicate = EqualPredicate.equalPredicate(null);
        assertNotNull(predicate);
        assertTrue(predicate.test(null));
    }
}
