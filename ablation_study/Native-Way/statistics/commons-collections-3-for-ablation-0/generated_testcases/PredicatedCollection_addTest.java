
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class PredicatedCollection_addTest {

    @Test
    public void testAddValidElement() {
        Predicate<String> truePredicate = TruePredicate.truePredicate();
        Collection<String> collection = new ArrayList<>();
        PredicatedCollection<String> predicatedCollection = new PredicatedCollection<>(collection, truePredicate);

        boolean result = predicatedCollection.add("validElement");

        assertTrue(result);
        assertTrue(collection.contains("validElement"));
    }

    @Test
    public void testAddInvalidElement() {
        Predicate<String> falsePredicate = object -> false;
        Collection<String> collection = new ArrayList<>();
        PredicatedCollection<String> predicatedCollection = new PredicatedCollection<>(collection, falsePredicate);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            predicatedCollection.add("invalidElement");
        });

        assertEquals("Cannot add Object 'invalidElement' - Predicate '" + falsePredicate + "' rejected it", exception.getMessage());
        assertFalse(collection.contains("invalidElement"));
    }
}
