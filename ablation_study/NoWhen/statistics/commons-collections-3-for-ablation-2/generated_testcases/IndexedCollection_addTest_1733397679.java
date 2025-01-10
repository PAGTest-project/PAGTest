
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class IndexedCollection_addTest {

    @Test
    public void testAdd() {
        // Given
        Transformer<String, Integer> keyTransformer = s -> s.length();
        IndexedCollection<Integer, String> indexedCollection = IndexedCollection.nonUniqueIndexedCollection(Collections.emptyList(), keyTransformer);

        // When
        boolean added = indexedCollection.add("test");

        // Then
        assertTrue(added);
        assertTrue(indexedCollection.contains("test"));
        assertEquals("test", indexedCollection.get(4));
    }
}
