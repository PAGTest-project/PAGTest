
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_containsTest {

    private CompositeCollection<String> compositeCollection;
    private Collection<String> collection1;
    private Collection<String> collection2;

    @BeforeEach
    public void setUp() {
        compositeCollection = new CompositeCollection<>();
        collection1 = new ArrayList<>(List.of("A", "B", "C"));
        collection2 = new ArrayList<>(List.of("D", "E", "F"));
        compositeCollection.addComposited(collection1, collection2);
    }

    @Test
    public void testContains_ElementPresentInFirstCollection() {
        assertTrue(compositeCollection.contains("A"));
    }

    @Test
    public void testContains_ElementPresentInSecondCollection() {
        assertTrue(compositeCollection.contains("D"));
    }

    @Test
    public void testContains_ElementNotPresent() {
        assertFalse(compositeCollection.contains("G"));
    }

    @Test
    public void testContains_EmptyCompositeCollection() {
        CompositeCollection<String> emptyComposite = new CompositeCollection<>();
        assertFalse(emptyComposite.contains("A"));
    }
}
