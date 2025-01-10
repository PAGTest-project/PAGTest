
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_removeIfTest {

    private CompositeCollection<String> compositeCollection;
    private Collection<String> collection1;
    private Collection<String> collection2;

    @BeforeEach
    public void setUp() {
        compositeCollection = new CompositeCollection<>();
        collection1 = new ArrayList<>();
        collection2 = new ArrayList<>();
        compositeCollection.addComposited(collection1);
        compositeCollection.addComposited(collection2);
    }

    @Test
    public void testRemoveIf_NullFilter() {
        assertFalse(compositeCollection.removeIf(null));
    }

    @Test
    public void testRemoveIf_NoChange() {
        collection1.add("A");
        collection2.add("B");

        Predicate<String> filter = s -> s.equals("C");
        assertFalse(compositeCollection.removeIf(filter));
        assertTrue(collection1.contains("A"));
        assertTrue(collection2.contains("B"));
    }

    @Test
    public void testRemoveIf_WithChange() {
        collection1.add("A");
        collection2.add("B");

        Predicate<String> filter = s -> s.equals("A");
        assertTrue(compositeCollection.removeIf(filter));
        assertFalse(collection1.contains("A"));
        assertTrue(collection2.contains("B"));
    }

    @Test
    public void testRemoveIf_MultipleCollections() {
        collection1.add("A");
        collection2.add("A");

        Predicate<String> filter = s -> s.equals("A");
        assertTrue(compositeCollection.removeIf(filter));
        assertFalse(collection1.contains("A"));
        assertFalse(collection2.contains("A"));
    }
}
