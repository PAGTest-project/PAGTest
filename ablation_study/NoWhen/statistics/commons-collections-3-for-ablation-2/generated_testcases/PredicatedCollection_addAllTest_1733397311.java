
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PredicatedCollection_addAllTest {

    @Test
    public void testAddAll() {
        Predicate<String> truePredicate = TruePredicate.truePredicate();
        PredicatedCollection<String> predicatedCollection = new PredicatedCollection<>(Arrays.asList("valid1", "valid2"), truePredicate);

        Collection<String> newItems = Arrays.asList("valid3", "valid4");
        boolean result = predicatedCollection.addAll(newItems);

        assertTrue(result);
        assertTrue(predicatedCollection.containsAll(newItems));
    }
}
