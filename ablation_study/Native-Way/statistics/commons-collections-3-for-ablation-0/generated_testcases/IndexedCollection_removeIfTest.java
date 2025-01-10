
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndexedCollection_removeIfTest {

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
        originalCollection.add("1");
        originalCollection.add("2");
        originalCollection.add("3");
    }

    @Test
    public void testRemoveIfWithNullFilter() {
        assertFalse(indexedCollection.removeIf(null));
    }

    @Test
    public void testRemoveIfWithNoMatchingElements() {
        Predicate<String> filter = s -> s.equals("4");
        assertFalse(indexedCollection.removeIf(filter));
    }

    @Test
    public void testRemoveIfWithMatchingElements() {
        Predicate<String> filter = s -> s.equals("2");
        assertTrue(indexedCollection.removeIf(filter));
        assertFalse(originalCollection.contains("2"));
        indexedCollection.reindex();
        assertNull(indexedCollection.get(2));
    }

    @Test
    public void testRemoveIfWithAllMatchingElements() {
        Predicate<String> filter = s -> true;
        assertTrue(indexedCollection.removeIf(filter));
        assertTrue(originalCollection.isEmpty());
        indexedCollection.reindex();
        assertNull(indexedCollection.get(1));
        assertNull(indexedCollection.get(2));
        assertNull(indexedCollection.get(3));
    }
}
