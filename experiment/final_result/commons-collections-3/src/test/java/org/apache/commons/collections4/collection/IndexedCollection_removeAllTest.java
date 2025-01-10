
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_removeAllTest {

    private IndexedCollection<Integer, String> indexedCollection;
    private Collection<String> originalCollection;

    @BeforeEach
    public void setUp() {
        originalCollection = new ArrayList<>(Arrays.asList("1", "2", "3"));
        indexedCollection = IndexedCollection.uniqueIndexedCollection(originalCollection, new Transformer<String, Integer>() {
            @Override
            public Integer transform(String input) {
                return Integer.parseInt(input);
            }
        });
    }

    @Test
    public void testRemoveAllRemovesAllElements() {
        Collection<String> toRemove = Arrays.asList("1", "2");
        assertTrue(indexedCollection.removeAll(toRemove));
        assertFalse(indexedCollection.contains("1"));
        assertFalse(indexedCollection.contains("2"));
        assertTrue(indexedCollection.contains("3"));
    }

    @Test
    public void testRemoveAllNoElementsToRemove() {
        Collection<String> toRemove = Arrays.asList("4", "5");
        assertFalse(indexedCollection.removeAll(toRemove));
        assertTrue(indexedCollection.contains("1"));
        assertTrue(indexedCollection.contains("2"));
        assertTrue(indexedCollection.contains("3"));
    }

    @Test
    public void testRemoveAllEmptyCollection() {
        Collection<String> toRemove = new ArrayList<>();
        assertFalse(indexedCollection.removeAll(toRemove));
        assertTrue(indexedCollection.contains("1"));
        assertTrue(indexedCollection.contains("2"));
        assertTrue(indexedCollection.contains("3"));
    }
}
