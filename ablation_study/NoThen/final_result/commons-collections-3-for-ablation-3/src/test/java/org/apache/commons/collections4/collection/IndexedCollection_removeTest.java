
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
        originalCollection = new ArrayList<>();
        indexedCollection = decorateUniqueCollection(originalCollection);
    }

    @Test
    public void testRemoveElementFromCollection() {
        originalCollection.add("1");
        originalCollection.add("2");
        originalCollection.add("3");

        indexedCollection.reindex();

        assertTrue(indexedCollection.remove("2"));
        assertFalse(indexedCollection.contains("2"));
        assertEquals(2, indexedCollection.size());
    }

    @Test
    public void testRemoveNonExistentElementFromCollection() {
        originalCollection.add("1");
        originalCollection.add("2");
        originalCollection.add("3");

        indexedCollection.reindex();

        assertFalse(indexedCollection.remove("4"));
        assertEquals(3, indexedCollection.size());
    }

    @Test
    public void testRemoveElementAndReindex() {
        originalCollection.add("1");
        originalCollection.add("2");
        originalCollection.add("3");

        indexedCollection.reindex();

        assertTrue(indexedCollection.remove("2"));
        indexedCollection.reindex();

        assertFalse(indexedCollection.contains("2"));
        assertEquals(2, indexedCollection.size());
    }

    private IndexedCollection<Integer, String> decorateUniqueCollection(Collection<String> original) {
        Transformer<String, Integer> keyTransformer = s -> Integer.valueOf(s);
        return IndexedCollection.uniqueIndexedCollection(original, keyTransformer);
    }
}
