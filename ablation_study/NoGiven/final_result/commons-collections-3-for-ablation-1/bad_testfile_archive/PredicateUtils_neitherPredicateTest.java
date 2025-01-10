
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.FalsePredicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PredicateUtils_neitherPredicateTest {

    @Test
    public void testNeitherPredicate() {
        Predicate<Object> truePredicate = TruePredicate.truePredicate();
        Predicate<Object> falsePredicate = FalsePredicate.falsePredicate();

        Predicate<Object> result = PredicateUtils.neitherPredicate(truePredicate, falsePredicate);

        assertTrue(result.equals(FalsePredicate.falsePredicate()));
    }
}
