
package org.apache.commons.collections4.collection;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class IndexedCollection_containsAllTest {

    @Test
    void testContainsAll_AllElementsPresent() {
        IndexedCollection<String, String> indexedCollection = new IndexedCollection<>(
                Arrays.asList("A", "B", "C"),
                s -> s,
                MultiValueMap.multiValueMap(new HashMap<>()),
                false
        );

        Collection<String> coll = Arrays.asList("A", "B");
        assertTrue(indexedCollection.containsAll(coll));
    }

    @Test
    void testContainsAll_SomeElementsMissing() {
        IndexedCollection<String, String> indexedCollection = new IndexedCollection<>(
                Arrays.asList("A", "B", "C"),
                s -> s,
                MultiValueMap.multiValueMap(new HashMap<>()),
                false
        );

        Collection<String> coll = Arrays.asList("A", "D");
        assertFalse(indexedCollection.containsAll(coll));
    }

    @Test
    void testContainsAll_EmptyCollection() {
        IndexedCollection<String, String> indexedCollection = new IndexedCollection<>(
                Arrays.asList("A", "B", "C"),
                s -> s,
                MultiValueMap.multiValueMap(new HashMap<>()),
                false
        );

        Collection<String> coll = Collections.emptyList();
        assertTrue(indexedCollection.containsAll(coll));
    }
}
