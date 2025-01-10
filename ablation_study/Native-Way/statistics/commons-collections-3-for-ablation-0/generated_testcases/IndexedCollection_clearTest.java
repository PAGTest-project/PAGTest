
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_clearTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Collection<String> originalCollection;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>();
        indexedCollection = decorateUniqueCollection(originalCollection);
    }

    @Test
    public void testClearRemovesAllElementsFromIndex() {
        originalCollection.add("1");
        originalCollection.add("2");
        originalCollection.add("3");

        indexedCollection.reindex();
        assertNotNull(indexedCollection.get(1));
        assertNotNull(indexedCollection.get(2));
        assertNotNull(indexedCollection.get(3));

        indexedCollection.clear();

        assertNull(indexedCollection.get(1));
        assertNull(indexedCollection.get(2));
        assertNull(indexedCollection.get(3));
    }

    @Test
    public void testClearRemovesAllElementsFromDecoratedCollection() {
        originalCollection.add("1");
        originalCollection.add("2");
        originalCollection.add("3");

        indexedCollection.reindex();
        assertFalse(originalCollection.isEmpty());

        indexedCollection.clear();

        assertTrue(originalCollection.isEmpty());
    }

    private IndexedCollection<Integer, String> decorateUniqueCollection(Collection<String> original) {
        Transformer<String, Integer> keyTransformer = s -> Integer.valueOf(s);
        return IndexedCollection.uniqueIndexedCollection(original, keyTransformer);
    }
}
