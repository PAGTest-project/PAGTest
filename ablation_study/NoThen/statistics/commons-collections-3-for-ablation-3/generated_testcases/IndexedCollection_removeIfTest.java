
package org.apache.commons.collections4.collection;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_removeIfTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Collection<String> originalCollection;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>(asList("1", "2", "3"));
        indexedCollection = decorateUniqueCollection(originalCollection);
    }

    @Test
    public void testRemoveIfWithNullFilter() {
        assertFalse(indexedCollection.removeIf(null));
    }

    @Test
    public void testRemoveIfWithNoMatches() {
        Predicate<String> filter = s -> s.equals("4");
        assertFalse(indexedCollection.removeIf(filter));
    }

    @Test
    public void testRemoveIfWithMatches() {
        Predicate<String> filter = s -> s.equals("2");
        assertTrue(indexedCollection.removeIf(filter));
        assertFalse(originalCollection.contains("2"));
        indexedCollection.reindex();
        assertNull(indexedCollection.get(2));
    }

    @Test
    public void testRemoveIfWithAllMatches() {
        Predicate<String> filter = s -> true;
        assertTrue(indexedCollection.removeIf(filter));
        assertTrue(originalCollection.isEmpty());
        indexedCollection.reindex();
        assertNull(indexedCollection.get(1));
        assertNull(indexedCollection.get(2));
        assertNull(indexedCollection.get(3));
    }

    private IndexedCollection<Integer, String> decorateUniqueCollection(Collection<String> original) {
        Transformer<String, Integer> keyTransformer = s -> Integer.valueOf(s);
        return IndexedCollection.uniqueIndexedCollection(original, keyTransformer);
    }
}
