
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_getTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Collection<String> originalCollection;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>();
        indexedCollection = decorateUniqueCollection(originalCollection);
    }

    @Test
    public void testGetReturnsFirstElementForKey() {
        originalCollection.add("1");
        originalCollection.add("2");
        originalCollection.add("3");
        indexedCollection.reindex();

        assertEquals("1", indexedCollection.get(1));
    }

    @Test
    public void testGetReturnsNullForNonExistentKey() {
        originalCollection.add("1");
        originalCollection.add("2");
        originalCollection.add("3");
        indexedCollection.reindex();

        assertNull(indexedCollection.get(4));
    }

    private IndexedCollection<Integer, String> decorateUniqueCollection(Collection<String> original) {
        Transformer<String, Integer> keyTransformer = s -> Integer.parseInt(s);
        return IndexedCollection.uniqueIndexedCollection(original, keyTransformer);
    }
}
