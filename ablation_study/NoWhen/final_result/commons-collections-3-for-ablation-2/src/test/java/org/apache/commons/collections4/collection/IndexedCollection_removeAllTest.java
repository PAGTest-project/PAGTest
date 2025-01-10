
package org.apache.commons.collections4.collection;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_removeAllTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Collection<String> originalCollection;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>(asList("1", "2", "3"));
        indexedCollection = decorateUniqueCollection(originalCollection);
    }

    @Test
    public void testRemoveAllRemovesAllElements() {
        Collection<String> elementsToRemove = asList("1", "2");
        assertTrue(indexedCollection.removeAll(elementsToRemove));
        assertFalse(indexedCollection.containsAll(elementsToRemove));
        assertEquals(1, indexedCollection.size());
    }

    @Test
    public void testRemoveAllNoElementsToRemove() {
        Collection<String> elementsToRemove = asList("4", "5");
        assertFalse(indexedCollection.removeAll(elementsToRemove));
        assertEquals(3, indexedCollection.size());
    }

    @Test
    public void testRemoveAllReindexAfterRemoval() {
        Collection<String> elementsToRemove = asList("1", "2");
        assertTrue(indexedCollection.removeAll(elementsToRemove));
        indexedCollection.reindex();
        assertNull(indexedCollection.get(1));
        assertNull(indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));
    }

    @Test
    public void testRemoveAllWithEmptyCollection() {
        Collection<String> elementsToRemove = new ArrayList<>();
        assertFalse(indexedCollection.removeAll(elementsToRemove));
        assertEquals(3, indexedCollection.size());
    }

    private IndexedCollection<Integer, String> decorateUniqueCollection(Collection<String> original) {
        Transformer<String, Integer> keyTransformer = s -> Integer.valueOf(s);
        return IndexedCollection.uniqueIndexedCollection(original, keyTransformer);
    }
}
