
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
        originalCollection = new ArrayList<>(asList("1", "2", "3"));
        indexedCollection = decorateUniqueCollection(originalCollection);
    }

    @Test
    public void testContainsAllReturnsTrueWhenAllElementsArePresent() {
        Collection<String> elements = asList("1", "2", "3");
        assertTrue(indexedCollection.containsAll(elements));
    }

    @Test
    public void testContainsAllReturnsFalseWhenNotAllElementsArePresent() {
        Collection<String> elements = asList("1", "2", "4");
        assertFalse(indexedCollection.containsAll(elements));
    }

    @Test
    public void testContainsAllReturnsTrueWhenCollectionIsEmpty() {
        Collection<String> elements = new ArrayList<>();
        assertTrue(indexedCollection.containsAll(elements));
    }

    @Test
    public void testContainsAllReturnsFalseWhenCollectionIsNull() {
        assertFalse(indexedCollection.containsAll(null));
    }

    @Test
    public void testContainsAllReturnsFalseWhenCollectionContainsNull() {
        Collection<String> elements = asList("1", "2", null);
        assertFalse(indexedCollection.containsAll(elements));
    }

    private IndexedCollection<Integer, String> decorateUniqueCollection(Collection<String> original) {
        Transformer<String, Integer> keyTransformer = s -> s == null ? null : Integer.valueOf(s);
        return IndexedCollection.uniqueIndexedCollection(original, keyTransformer);
    }
}
