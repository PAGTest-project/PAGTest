
package org.apache.commons.collections4.collection;

import static java.util.Arrays.asList;
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
        indexedCollection = IndexedCollection.uniqueIndexedCollection(originalCollection, new Transformer<String, Integer>() {
            @Override
            public Integer transform(String input) {
                return Integer.parseInt(input);
            }
        });
    }

    @Test
    public void testContainsReturnsTrueForExistingElement() {
        originalCollection.add("1");
        originalCollection.add("2");
        originalCollection.add("3");
        indexedCollection.reindex();

        assertTrue(indexedCollection.contains("1"));
        assertTrue(indexedCollection.contains("2"));
        assertTrue(indexedCollection.contains("3"));
    }

    @Test
    public void testContainsReturnsFalseForNonExistingElement() {
        originalCollection.add("1");
        originalCollection.add("2");
        indexedCollection.reindex();

        assertFalse(indexedCollection.contains("3"));
        assertFalse(indexedCollection.contains("4"));
    }

    @Test
    public void testContainsAfterReindex() {
        originalCollection.add("1");
        originalCollection.add("2");
        indexedCollection.reindex();

        assertTrue(indexedCollection.contains("1"));
        assertTrue(indexedCollection.contains("2"));

        originalCollection.add("3");
        indexedCollection.reindex();

        assertTrue(indexedCollection.contains("3"));
    }

    @Test
    public void testContainsAfterElementRemoval() {
        originalCollection.add("1");
        originalCollection.add("2");
        indexedCollection.reindex();

        assertTrue(indexedCollection.contains("1"));
        assertTrue(indexedCollection.contains("2"));

        originalCollection.remove("1");
        indexedCollection.reindex();

        assertFalse(indexedCollection.contains("1"));
        assertTrue(indexedCollection.contains("2"));
    }
}
