
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_retainAllTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Collection<String> originalCollection;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>(Arrays.asList("1", "2", "3"));
        indexedCollection = decorateUniqueCollection(originalCollection);
    }

    @Test
    public void testRetainAllWithMatchingElements() {
        Collection<String> toRetain = Arrays.asList("2", "3");
        boolean changed = indexedCollection.retainAll(toRetain);

        assertTrue(changed);
        assertEquals(2, indexedCollection.size());
        assertTrue(indexedCollection.containsAll(toRetain));
    }

    @Test
    public void testRetainAllWithNoMatchingElements() {
        Collection<String> toRetain = Arrays.asList("4", "5");
        boolean changed = indexedCollection.retainAll(toRetain);

        assertTrue(changed);
        assertEquals(0, indexedCollection.size());
    }

    @Test
    public void testRetainAllWithAllMatchingElements() {
        Collection<String> toRetain = Arrays.asList("1", "2", "3");
        boolean changed = indexedCollection.retainAll(toRetain);

        assertFalse(changed);
        assertEquals(3, indexedCollection.size());
        assertTrue(indexedCollection.containsAll(toRetain));
    }

    private IndexedCollection<Integer, String> decorateUniqueCollection(Collection<String> original) {
        Transformer<String, Integer> keyTransformer = s -> Integer.valueOf(s);
        return IndexedCollection.uniqueIndexedCollection(original, keyTransformer);
    }
}
