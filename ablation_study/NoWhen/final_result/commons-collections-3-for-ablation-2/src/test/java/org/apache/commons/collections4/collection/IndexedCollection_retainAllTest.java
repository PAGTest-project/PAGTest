
package org.apache.commons.collections4.collection;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_retainAllTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Collection<String> originalCollection;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>(asList("1", "2", "3"));
        indexedCollection = decorateUniqueCollection(originalCollection);
    }

    @Test
    public void testRetainAllWithMatchingElements() {
        Collection<String> retainCollection = asList("1", "2");
        boolean changed = indexedCollection.retainAll(retainCollection);

        assertTrue(changed);
        assertEquals(2, indexedCollection.size());
        assertTrue(indexedCollection.containsAll(retainCollection));
    }

    @Test
    public void testRetainAllWithNoMatchingElements() {
        Collection<String> retainCollection = asList("4", "5");
        boolean changed = indexedCollection.retainAll(retainCollection);

        assertTrue(changed);
        assertEquals(0, indexedCollection.size());
    }

    @Test
    public void testRetainAllWithAllMatchingElements() {
        Collection<String> retainCollection = asList("1", "2", "3");
        boolean changed = indexedCollection.retainAll(retainCollection);

        assertFalse(changed);
        assertEquals(3, indexedCollection.size());
        assertTrue(indexedCollection.containsAll(retainCollection));
    }

    @Test
    public void testRetainAllWithEmptyCollection() {
        Collection<String> retainCollection = new ArrayList<>();
        boolean changed = indexedCollection.retainAll(retainCollection);

        assertTrue(changed);
        assertEquals(0, indexedCollection.size());
    }

    @Test
    public void testRetainAllWithNullCollection() {
        assertThrows(NullPointerException.class, () -> indexedCollection.retainAll(null));
    }

    private IndexedCollection<Integer, String> decorateUniqueCollection(Collection<String> original) {
        Transformer<String, Integer> keyTransformer = s -> Integer.valueOf(s);
        return IndexedCollection.uniqueIndexedCollection(original, keyTransformer);
    }
}
