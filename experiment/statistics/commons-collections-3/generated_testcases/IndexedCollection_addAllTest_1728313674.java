
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        indexedCollection = IndexedCollection.uniqueIndexedCollection(originalCollection, new Transformer<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.parseInt(input);
            }
        });
    }

    @Test
    public void testAddAll_AllElementsAdded() {
        Collection<String> elementsToAdd = Arrays.asList("1", "2", "3");
        assertTrue(indexedCollection.addAll(elementsToAdd));
        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));
    }

    @Test
    public void testAddAll_NoElementsAdded() {
        Collection<String> elementsToAdd = Arrays.asList();
        assertFalse(indexedCollection.addAll(elementsToAdd));
    }

    @Test
    public void testAddAll_DuplicateElements() {
        Collection<String> elementsToAdd = Arrays.asList("1", "1");
        assertThrows(IllegalArgumentException.class, () -> indexedCollection.addAll(elementsToAdd));
    }
}
