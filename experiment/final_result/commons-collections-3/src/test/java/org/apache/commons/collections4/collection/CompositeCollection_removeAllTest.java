
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_removeAllTest {

    private CompositeCollection<String> compositeCollection;
    private Collection<String> collection1;
    private Collection<String> collection2;

    @BeforeEach
    public void setUp() {
        compositeCollection = new CompositeCollection<>();
        collection1 = new ArrayList<>(Arrays.asList("1", "2", "3"));
        collection2 = new HashSet<>(Arrays.asList("3", "4", "5"));
        compositeCollection.addComposited(collection1, collection2);
    }

    @Test
    public void testRemoveAllWithNonEmptyCollection() {
        Collection<String> toRemove = Arrays.asList("2", "4");
        assertTrue(compositeCollection.removeAll(toRemove));
        assertFalse(collection1.contains("2"));
        assertFalse(collection2.contains("4"));
    }

    @Test
    public void testRemoveAllWithEmptyCollection() {
        Collection<String> toRemove = new ArrayList<>();
        assertFalse(compositeCollection.removeAll(toRemove));
        assertTrue(collection1.contains("1"));
        assertTrue(collection2.contains("3"));
    }

    @Test
    public void testRemoveAllWithNullCollection() {
        assertFalse(compositeCollection.removeAll(null));
        assertTrue(collection1.contains("1"));
        assertTrue(collection2.contains("3"));
    }
}
