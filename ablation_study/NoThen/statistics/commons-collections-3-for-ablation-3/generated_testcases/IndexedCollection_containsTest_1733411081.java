
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
    private Transformer<String, Integer> keyTransformer;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>();
        keyTransformer = new Transformer<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.parseInt(input);
            }
        };
        indexedCollection = IndexedCollection.nonUniqueIndexedCollection(originalCollection, keyTransformer);
    }

    @Test
    public void testContainsReturnsTrueForExistingObject() {
        originalCollection.add("1");
        indexedCollection.reindex();
        assertTrue(indexedCollection.contains("1"));
    }

    @Test
    public void testContainsReturnsFalseForNonExistingObject() {
        originalCollection.add("1");
        indexedCollection.reindex();
        assertFalse(indexedCollection.contains("2"));
    }

    @Test
    public void testContainsReturnsTrueAfterAddingObject() {
        originalCollection.add("1");
        indexedCollection.reindex();
        originalCollection.add("2");
        indexedCollection.addToIndex("2");
        assertTrue(indexedCollection.contains("2"));
    }

    @Test
    public void testContainsReturnsFalseAfterRemovingObject() {
        originalCollection.add("1");
        indexedCollection.reindex();
        indexedCollection.removeFromIndex("1");
        assertFalse(indexedCollection.contains("1"));
    }
}
