
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PredicatedCollection_addAllTest {

    @Test
    public void testAddAll_ValidElements() {
        Predicate<String> predicate = NotNullPredicate.notNullPredicate();
        PredicatedCollection<String> predicatedCollection = new PredicatedCollection<>(Arrays.asList("valid1", "valid2"), predicate);
        Collection<String> validElements = Arrays.asList("valid3", "valid4");

        boolean result = predicatedCollection.addAll(validElements);

        assertTrue(result);
    }

    @Test
    public void testAddAll_InvalidElement() {
        Predicate<String> predicate = NotNullPredicate.notNullPredicate();
        PredicatedCollection<String> predicatedCollection = new PredicatedCollection<>(Arrays.asList("valid1", "valid2"), predicate);
        Collection<String> invalidElements = Arrays.asList("valid3", null);

        assertThrows(IllegalArgumentException.class, () -> {
            predicatedCollection.addAll(invalidElements);
        });
    }
}
