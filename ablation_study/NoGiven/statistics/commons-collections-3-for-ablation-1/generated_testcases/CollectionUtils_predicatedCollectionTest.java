
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtils_predicatedCollectionTest {

    @Test
    void testPredicatedCollection_Success() {
        Collection<String> collection = new ArrayList<>();
        collection.add("test");
        Predicate<String> predicate = TruePredicate.truePredicate();

        Collection<String> result = CollectionUtils.predicatedCollection(collection, predicate);

        assertNotNull(result);
        assertEquals(collection.size(), result.size());
        assertTrue(result.containsAll(collection));
    }

    @Test
    void testPredicatedCollection_NullCollection() {
        Predicate<String> predicate = TruePredicate.truePredicate();

        Exception exception = assertThrows(NullPointerException.class, () -> {
            CollectionUtils.predicatedCollection(null, predicate);
        });

        assertEquals("collection", exception.getMessage());
    }

    @Test
    void testPredicatedCollection_NullPredicate() {
        Collection<String> collection = new ArrayList<>();
        collection.add("test");

        Exception exception = assertThrows(NullPointerException.class, () -> {
            CollectionUtils.predicatedCollection(collection, null);
        });

        assertEquals("predicate", exception.getMessage());
    }
}
