
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_containsAllTest {

    private CompositeCollection<String> compositeCollection;
    private Collection<String> collection1;
    private Collection<String> collection2;

    @BeforeEach
    public void setUp() {
        compositeCollection = new CompositeCollection<>();
        collection1 = new ArrayList<>(Arrays.asList("A", "B", "C"));
        collection2 = new ArrayList<>(Arrays.asList("D", "E", "F"));
        compositeCollection.addComposited(collection1, collection2);
    }

    @Test
    public void testContainsAll_WithNullCollection() {
        assertFalse(compositeCollection.containsAll(null));
    }

    @Test
    public void testContainsAll_WithEmptyCollection() {
        assertTrue(compositeCollection.containsAll(new ArrayList<>()));
    }

    @Test
    public void testContainsAll_WithAllElementsPresent() {
        Collection<String> testCollection = Arrays.asList("A", "D");
        assertTrue(compositeCollection.containsAll(testCollection));
    }

    @Test
    public void testContainsAll_WithSomeElementsMissing() {
        Collection<String> testCollection = Arrays.asList("A", "X");
        assertFalse(compositeCollection.containsAll(testCollection));
    }

    @Test
    public void testContainsAll_WithAllElementsMissing() {
        Collection<String> testCollection = Arrays.asList("X", "Y");
        assertFalse(compositeCollection.containsAll(testCollection));
    }
}
