
package org.apache.commons.collections4.collection;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_removeTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Collection<String> originalCollection;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>(asList("1", "2", "3"));
        indexedCollection = decorateUniqueCollection(originalCollection);
    }

    @Test
    public void testRemoveSuccess() {
        assertTrue(indexedCollection.remove("1"));
        assertFalse(indexedCollection.contains("1"));
        assertNull(indexedCollection.get(1));
    }

    @Test
    public void testRemoveFailure() {
        assertFalse(indexedCollection.remove("4"));
        assertTrue(indexedCollection.contains("1"));
        assertEquals("1", indexedCollection.get(1));
    }

    @Test
    public void testRemoveAll() {
        Collection<String> toRemove = new ArrayList<>(asList("1", "2"));
        assertTrue(indexedCollection.removeAll(toRemove));
        assertFalse(indexedCollection.contains("1"));
        assertFalse(indexedCollection.contains("2"));
        assertNull(indexedCollection.get(1));
        assertNull(indexedCollection.get(2));
    }

    @Test
    public void testReindexAfterRemove() {
        indexedCollection.remove("1");
        indexedCollection.reindex();
        assertFalse(indexedCollection.contains("1"));
        assertNull(indexedCollection.get(1));
    }

    private IndexedCollection<Integer, String> decorateUniqueCollection(Collection<String> original) {
        Transformer<String, Integer> keyTransformer = s -> Integer.valueOf(s);
        return IndexedCollection.uniqueIndexedCollection(original, keyTransformer);
    }
}
