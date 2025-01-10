
package org.apache.commons.collections4.collection;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformedCollection_transformedCollectionTest {

    @Test
    public void testTransformedCollectionWithNonEmptyCollection() {
        // Given
        Collection<String> collection = new ArrayList<>(Arrays.asList("a", "b", "c"));
        Transformer<String, String> transformer = s -> s.toUpperCase();

        // When
        TransformedCollection<String> transformedCollection = TransformedCollection.transformedCollection(collection, transformer);

        // Then
        assertEquals(3, transformedCollection.size());
        assertTrue(transformedCollection.contains("A"));
        assertTrue(transformedCollection.contains("B"));
        assertTrue(transformedCollection.contains("C"));
    }

    @Test
    public void testTransformedCollectionWithEmptyCollection() {
        // Given
        Collection<String> collection = Collections.emptyList();
        Transformer<String, String> transformer = s -> s.toUpperCase();

        // When
        TransformedCollection<String> transformedCollection = TransformedCollection.transformedCollection(collection, transformer);

        // Then
        assertTrue(transformedCollection.isEmpty());
    }
}
