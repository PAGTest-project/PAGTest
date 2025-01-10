
package org.apache.commons.collections4.collection;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_addAllTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Collection<String> originalCollection;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>();
        indexedCollection = decorateUniqueCollection(originalCollection);
    }

    @Test
    public void testAddAllWithUniqueIndex() {
        Collection<String> toAdd = asList("1", "2", "3");
        assertTrue(indexedCollection.addAll(toAdd));
        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));
    }

    @Test
    public void testAddAllWithDuplicateKey() {
        Collection<String> toAdd = asList("1", "2", "3");
        assertThrows(IllegalArgumentException.class, () -> indexedCollection.addAll(toAdd));
    }

    @Test
    public void testAddAllWithEmptyCollection() {
        Collection<String> toAdd = new ArrayList<>();
        assertFalse(indexedCollection.addAll(toAdd));
    }

    @Test
    public void testAddAllWithNullElements() {
        Collection<String> toAdd = asList("1", null, "3");
        assertThrows(NullPointerException.class, () -> indexedCollection.addAll(toAdd));
    }

    private IndexedCollection<Integer, String> decorateUniqueCollection(Collection<String> original) {
        Transformer<String, Integer> keyTransformer = s -> Integer.parseInt(s);
        return IndexedCollection.uniqueIndexedCollection(original, keyTransformer);
    }
}
