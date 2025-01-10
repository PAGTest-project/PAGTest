
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
        indexedCollection = IndexedCollection.uniqueIndexedCollection(originalCollection, new Transformer<String, Integer>() {
            @Override
            public Integer transform(String input) {
                if (input == null) {
                    throw new NullPointerException("Input cannot be null");
                }
                return Integer.parseInt(input);
            }
        });
    }

    @Test
    public void testAddAllSuccess() {
        Collection<String> elementsToAdd = asList("1", "2", "3");
        assertTrue(indexedCollection.addAll(elementsToAdd));
        assertTrue(indexedCollection.containsAll(elementsToAdd));
        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));
    }

    @Test
    public void testAddAllNoChange() {
        Collection<String> elementsToAdd = new ArrayList<>();
        assertFalse(indexedCollection.addAll(elementsToAdd));
        assertTrue(indexedCollection.isEmpty());
    }

    @Test
    public void testAddAllWithDuplicateKey() {
        Collection<String> elementsToAdd = asList("1", "2", "2");
        assertThrows(IllegalArgumentException.class, () -> indexedCollection.addAll(elementsToAdd));
    }

    @Test
    public void testAddAllWithEmptyCollection() {
        Collection<String> elementsToAdd = new ArrayList<>();
        assertFalse(indexedCollection.addAll(elementsToAdd));
        assertTrue(indexedCollection.isEmpty());
    }

    @Test
    public void testAddAllWithNullElement() {
        Collection<String> elementsToAdd = asList("1", null, "3");
        assertThrows(NullPointerException.class, () -> indexedCollection.addAll(elementsToAdd));
    }
}
