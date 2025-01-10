
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;

public class AllPredicate_allPredicateTest {

    @Test
    public void testAllPredicate_EmptyCollection() {
        Collection<Predicate<Object>> predicates = Arrays.asList();
        Predicate<Object> result = AllPredicate.allPredicate(predicates);
        assertTrue(result.test(new Object()));
    }

    @Test
    public void testAllPredicate_SinglePredicate() {
        Predicate<Object> singlePredicate = obj -> true;
        Collection<Predicate<Object>> predicates = Arrays.asList(singlePredicate);
        Predicate<Object> result = AllPredicate.allPredicate(predicates);
        assertSame(singlePredicate, result);
    }

    @Test
    public void testAllPredicate_MultiplePredicates() {
        Predicate<Object> predicate1 = obj -> true;
        Predicate<Object> predicate2 = obj -> true;
        Collection<Predicate<Object>> predicates = Arrays.asList(predicate1, predicate2);
        Predicate<Object> result = AllPredicate.allPredicate(predicates);
        assertTrue(result instanceof AllPredicate);
    }
}
