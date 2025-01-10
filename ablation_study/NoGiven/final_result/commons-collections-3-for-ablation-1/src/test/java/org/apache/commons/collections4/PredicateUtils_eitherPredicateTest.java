
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.TruePredicate;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PredicateUtils_eitherPredicateTest {

    @Test
    public void testEitherPredicate() {
        Predicate<Object> truePredicate = TruePredicate.truePredicate();
        Predicate<Object> falsePredicate = FalsePredicate.falsePredicate();

        Predicate<Object> eitherTrueOrFalse = PredicateUtils.eitherPredicate(truePredicate, falsePredicate);
        assertTrue(eitherTrueOrFalse.evaluate(new Object()));

        Predicate<Object> eitherFalseOrFalse = PredicateUtils.eitherPredicate(falsePredicate, falsePredicate);
        assertFalse(eitherFalseOrFalse.evaluate(new Object()));
    }
}
