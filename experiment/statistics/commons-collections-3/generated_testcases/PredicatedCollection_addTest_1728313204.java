
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class PredicatedCollection_addTest {

    @Test
    public void testAddValidElement() {
        Predicate<String> predicate = NotNullPredicate.notNullPredicate();
        PredicatedCollection<String> predicatedCollection = new PredicatedCollection<>(Collections.emptyList(), predicate);

        boolean result = predicatedCollection.add("validElement");

        assertTrue(result);
    }

    @Test
    public void testAddInvalidElement() {
        Predicate<String> predicate = NotNullPredicate.notNullPredicate();
        PredicatedCollection<String> predicatedCollection = new PredicatedCollection<>(Collections.emptyList(), predicate);

        assertThrows(IllegalArgumentException.class, () -> {
            predicatedCollection.add(null);
        });
    }
}
