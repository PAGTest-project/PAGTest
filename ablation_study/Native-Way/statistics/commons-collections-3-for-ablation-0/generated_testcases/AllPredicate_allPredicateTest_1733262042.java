
package org.apache.commons.collections4.functors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;

public class AllPredicate_allPredicateTest {

    @Test
    public void testAllPredicate_EmptyCollection() {
        Collection<Predicate<Object>> predicates = Arrays.asList();
        Predicate<Object> result = AllPredicate.allPredicate(predicates);
        assertTrue(result.test(new Object()));
    }

    @Test
    public void testAllPredicate_SinglePredicate() {
        Predicate<Object> mockPredicate = mock(Predicate.class);
        when(mockPredicate.test(new Object())).thenReturn(true);
        Collection<Predicate<Object>> predicates = Arrays.asList(mockPredicate);
        Predicate<Object> result = AllPredicate.allPredicate(predicates);
        assertTrue(result.test(new Object()));
    }

    @Test
    public void testAllPredicate_MultiplePredicates() {
        Predicate<Object> mockPredicate1 = mock(Predicate.class);
        Predicate<Object> mockPredicate2 = mock(Predicate.class);
        when(mockPredicate1.test(new Object())).thenReturn(true);
        when(mockPredicate2.test(new Object())).thenReturn(true);
        Collection<Predicate<Object>> predicates = Arrays.asList(mockPredicate1, mockPredicate2);
        Predicate<Object> result = AllPredicate.allPredicate(predicates);
        assertTrue(result.test(new Object()));
    }
}
