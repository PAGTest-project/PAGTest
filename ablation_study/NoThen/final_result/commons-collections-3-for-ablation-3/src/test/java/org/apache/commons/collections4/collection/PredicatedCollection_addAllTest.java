
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class PredicatedCollection_addAllTest {

    @Test
    public void testAddAll_ValidElements() {
        Predicate<String> predicate = NotNullPredicate.notNullPredicate();
        PredicatedCollection<String> predicatedCollection = new PredicatedCollection<>(new HashSet<>(), predicate);
        Collection<String> validElements = Arrays.asList("item1", "item2");

        boolean result = predicatedCollection.addAll(validElements);

        assertTrue(result);
        assertEquals(2, predicatedCollection.size());
    }

    @Test
    public void testAddAll_InvalidElement() {
        Predicate<String> predicate = NotNullPredicate.notNullPredicate();
        PredicatedCollection<String> predicatedCollection = new PredicatedCollection<>(new HashSet<>(), predicate);
        Collection<String> invalidElements = Arrays.asList("item1", null, "item2");

        assertThrows(IllegalArgumentException.class, () -> {
            predicatedCollection.addAll(invalidElements);
        });
    }
}
