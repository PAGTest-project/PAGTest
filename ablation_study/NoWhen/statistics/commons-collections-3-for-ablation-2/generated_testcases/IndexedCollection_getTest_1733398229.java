
package org.apache.commons.collections4.collection;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        indexedCollection = IndexedCollection.uniqueIndexedCollection(originalCollection, new Transformer<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.parseInt(input);
            }
        });
    }

    @Test
    public void testGetReturnsFirstElementForExistingKey() {
        originalCollection.add("1");
        originalCollection.add("2");
        originalCollection.add("3");
        indexedCollection.reindex();

        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));
    }

    @Test
    public void testGetReturnsNullForNonExistingKey() {
        originalCollection.add("1");
        originalCollection.add("2");
        indexedCollection.reindex();

        assertNull(indexedCollection.get(3));
    }

    @Test
    public void testGetReturnsNullForEmptyCollection() {
        assertNull(indexedCollection.get(1));
    }

    @Test
    public void testGetReturnsFirstElementAfterReindex() {
        originalCollection.add("1");
        originalCollection.add("2");
        indexedCollection.reindex();

        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));

        originalCollection.add("3");
        indexedCollection.reindex();

        assertEquals("1", indexedCollection.get(1));
        assertEquals("2", indexedCollection.get(2));
        assertEquals("3", indexedCollection.get(3));
    }
}
