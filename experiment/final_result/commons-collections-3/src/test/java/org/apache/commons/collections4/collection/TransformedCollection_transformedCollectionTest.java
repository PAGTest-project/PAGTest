
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
        Collection<String> originalCollection = new ArrayList<>(Arrays.asList("a", "b", "c"));
        Transformer<String, String> transformer = s -> s.toUpperCase();

        TransformedCollection<String> transformedCollection = TransformedCollection.transformedCollection(originalCollection, transformer);

        assertEquals(3, transformedCollection.size());
        assertTrue(transformedCollection.contains("A"));
        assertTrue(transformedCollection.contains("B"));
        assertTrue(transformedCollection.contains("C"));
    }

    @Test
    public void testTransformedCollectionWithEmptyCollection() {
        Collection<String> originalCollection = Collections.emptyList();
        Transformer<String, String> transformer = s -> s.toUpperCase();

        TransformedCollection<String> transformedCollection = TransformedCollection.transformedCollection(originalCollection, transformer);

        assertTrue(transformedCollection.isEmpty());
    }
}
