
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_containsTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Collection<String> originalCollection;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>();
        indexedCollection = decorateUniqueCollection(originalCollection);
    }

    @Test
    public void testContainsReturnsTrueForExistingElement() {
        originalCollection.add("1");
        indexedCollection.reindex();
        assertTrue(indexedCollection.contains("1"));
    }

    @Test
    public void testContainsReturnsFalseForNonExistingElement() {
        originalCollection.add("1");
        indexedCollection.reindex();
        assertFalse(indexedCollection.contains("2"));
    }

    @Test
    public void testContainsReturnsFalseForNullElement() {
        originalCollection.add("1");
        indexedCollection.reindex();
        assertFalse(indexedCollection.contains(null));
    }

    @Test
    public void testContainsReturnsTrueAfterAddingElement() {
        originalCollection.add("1");
        indexedCollection.reindex();
        originalCollection.add("2");
        indexedCollection.reindex();
        assertTrue(indexedCollection.contains("2"));
    }

    @Test
    public void testContainsReturnsFalseAfterRemovingElement() {
        originalCollection.add("1");
        indexedCollection.reindex();
        originalCollection.remove("1");
        indexedCollection.reindex();
        assertFalse(indexedCollection.contains("1"));
    }

    private IndexedCollection<Integer, String> decorateUniqueCollection(Collection<String> original) {
        Transformer<String, Integer> keyTransformer = s -> Integer.valueOf(s);
        return IndexedCollection.uniqueIndexedCollection(original, keyTransformer);
    }
}
