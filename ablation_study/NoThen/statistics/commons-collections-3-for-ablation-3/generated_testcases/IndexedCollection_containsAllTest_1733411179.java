
package org.apache.commons.collections4.collection;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_containsAllTest {

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
    public void testContainsAllReturnsTrueWhenAllElementsArePresent() {
        originalCollection.add("1");
        originalCollection.add("2");
        originalCollection.add("3");
        indexedCollection.reindex();

        Collection<String> testCollection = asList("1", "2", "3");
        assertTrue(indexedCollection.containsAll(testCollection));
    }

    @Test
    public void testContainsAllReturnsFalseWhenNotAllElementsArePresent() {
        originalCollection.add("1");
        originalCollection.add("2");
        indexedCollection.reindex();

        Collection<String> testCollection = asList("1", "2", "3");
        assertFalse(indexedCollection.containsAll(testCollection));
    }

    @Test
    public void testContainsAllReturnsTrueWhenCollectionIsEmpty() {
        Collection<String> testCollection = new ArrayList<>();
        assertTrue(indexedCollection.containsAll(testCollection));
    }

    @Test
    public void testContainsAllReturnsTrueWhenCollectionIsNull() {
        Collection<String> testCollection = null;
        assertTrue(indexedCollection.containsAll(testCollection));
    }
}
