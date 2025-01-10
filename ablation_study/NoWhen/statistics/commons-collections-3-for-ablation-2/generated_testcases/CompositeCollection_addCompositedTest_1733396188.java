
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_addCompositedTest {

    private CompositeCollection<String> compositeCollection;
    private Collection<String> collection1;
    private Collection<String> collection2;

    @BeforeEach
    public void setUp() {
        compositeCollection = new CompositeCollection<>();
        collection1 = new HashSet<>();
        collection2 = new HashSet<>();
    }

    @Test
    public void testAddCompositedSingleCollection() {
        collection1.add("element1");
        compositeCollection.addComposited(collection1);
        assertEquals(1, compositeCollection.size());
        assertTrue(compositeCollection.contains("element1"));
    }

    @Test
    public void testAddCompositedNullCollection() {
        compositeCollection.addComposited(null);
        assertEquals(0, compositeCollection.size());
    }

    @Test
    public void testAddCompositedMultipleCollections() {
        collection1.add("element1");
        collection2.add("element2");
        compositeCollection.addComposited(collection1);
        compositeCollection.addComposited(collection2);
        assertEquals(2, compositeCollection.size());
        assertTrue(compositeCollection.contains("element1"));
        assertTrue(compositeCollection.contains("element2"));
    }

    @Test
    public void testAddCompositedEmptyCollection() {
        compositeCollection.addComposited(collection1);
        assertEquals(0, compositeCollection.size());
    }

    @Test
    public void testAddCompositedDuplicateCollections() {
        collection1.add("element1");
        compositeCollection.addComposited(collection1);
        compositeCollection.addComposited(collection1);
        assertEquals(1, compositeCollection.size());
        assertTrue(compositeCollection.contains("element1"));
    }

    @Test
    public void testAddCompositedWithGetCollections() {
        collection1.add("element1");
        compositeCollection.addComposited(collection1);
        List<Collection<String>> collections = compositeCollection.getCollections();
        assertEquals(1, collections.size());
        assertTrue(collections.contains(collection1));
    }
}
