
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_addCompositedTest {

    private CompositeCollection<String> compositeCollection;
    private List<String> collection1;
    private List<String> collection2;

    @BeforeEach
    public void setUp() {
        compositeCollection = new CompositeCollection<>();
        collection1 = new ArrayList<>(List.of("A", "B"));
        collection2 = new ArrayList<>(List.of("C", "D"));
    }

    @Test
    public void testAddComposited_NonNullCollection() {
        compositeCollection.addComposited(collection1);
        assertEquals(1, compositeCollection.getCollections().size());
        assertTrue(compositeCollection.getCollections().contains(collection1));
    }

    @Test
    public void testAddComposited_NullCollection() {
        compositeCollection.addComposited(null);
        assertEquals(0, compositeCollection.getCollections().size());
    }

    @Test
    public void testAddComposited_MultipleCollections() {
        compositeCollection.addComposited(new Collection[]{collection1, collection2});
        assertEquals(2, compositeCollection.getCollections().size());
        assertTrue(compositeCollection.getCollections().contains(collection1));
        assertTrue(compositeCollection.getCollections().contains(collection2));
    }

    @Test
    public void testAddComposited_ArrayOfCollections() {
        Collection<String>[] collections = new Collection[]{collection1, collection2};
        compositeCollection.addComposited(collections);
        assertEquals(2, compositeCollection.getCollections().size());
        assertTrue(compositeCollection.getCollections().contains(collection1));
        assertTrue(compositeCollection.getCollections().contains(collection2));
    }
}
