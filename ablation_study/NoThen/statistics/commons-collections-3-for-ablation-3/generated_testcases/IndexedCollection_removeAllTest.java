
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
        Collection<String> toRemove = asList("1", "2");
        assertTrue(indexedCollection.removeAll(toRemove));
        assertFalse(indexedCollection.contains("1"));
        assertFalse(indexedCollection.contains("2"));
        assertTrue(indexedCollection.contains("3"));
    }

    @Test
    public void testRemoveAllReturnsFalseIfNoElementsRemoved() {
        Collection<String> toRemove = asList("4", "5");
        assertFalse(indexedCollection.removeAll(toRemove));
        assertTrue(indexedCollection.contains("1"));
        assertTrue(indexedCollection.contains("2"));
        assertTrue(indexedCollection.contains("3"));
    }

    @Test
    public void testRemoveAllReindexesAfterRemoval() {
        Collection<String> toRemove = asList("1", "2");
        indexedCollection.removeAll(toRemove);
        assertEquals("3", indexedCollection.get(3));
        assertNull(indexedCollection.get(1));
        assertNull(indexedCollection.get(2));
    }

    private IndexedCollection<Integer, String> decorateUniqueCollection(Collection<String> original) {
        Transformer<String, Integer> keyTransformer = s -> Integer.valueOf(s);
        return IndexedCollection.uniqueIndexedCollection(original, keyTransformer);
    }
}
