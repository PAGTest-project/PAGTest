
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class PredicatedCollection_addTest {

    @Test
    public void testAddValidElement() {
        Predicate<String> predicate = NotNullPredicate.notNullPredicate();
        PredicatedCollection<String> predicatedCollection = new PredicatedCollection<>(new ArrayList<>(), predicate);

        boolean result = predicatedCollection.add("validElement");

        assertTrue(result);
        assertEquals(1, predicatedCollection.size());
    }

    @Test
    public void testAddInvalidElement() {
        Predicate<String> predicate = NotNullPredicate.notNullPredicate();
        PredicatedCollection<String> predicatedCollection = new PredicatedCollection<>(new ArrayList<>(), predicate);

        assertThrows(IllegalArgumentException.class, () -> {
            predicatedCollection.add(null);
        });
    }
}
