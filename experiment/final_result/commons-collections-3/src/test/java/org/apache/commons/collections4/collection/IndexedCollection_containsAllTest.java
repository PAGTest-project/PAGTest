
package org.apache.commons.collections4.collection;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import org.apache.commons.collections4.map.MultiValueMap;
import static org.junit.jupiter.api.Assertions.*;

public class IndexedCollection_containsAllTest {

    @Test
    public void testContainsAll_AllElementsPresent() {
        // Given
        Collection<String> coll = Arrays.asList("A", "B", "C");
        IndexedCollection<String, String> indexedCollection = new IndexedCollection<>(coll, Object::toString, MultiValueMap.multiValueMap(new HashMap<>()), false);

        // When
        boolean result = indexedCollection.containsAll(Arrays.asList("A", "B"));

        // Then
        assertTrue(result);
    }

    @Test
    public void testContainsAll_SomeElementsMissing() {
        // Given
        Collection<String> coll = Arrays.asList("A", "B", "C");
        IndexedCollection<String, String> indexedCollection = new IndexedCollection<>(coll, Object::toString, MultiValueMap.multiValueMap(new HashMap<>()), false);

        // When
        boolean result = indexedCollection.containsAll(Arrays.asList("A", "D"));

        // Then
        assertFalse(result);
    }
}
