
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_retainAllTest {

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
    public void testRetainAllWithNonNullCollection() {
        Collection<String> retainCollection = new ArrayList<>(Arrays.asList("2", "3", "4"));
        boolean changed = compositeCollection.retainAll(retainCollection);
        assertTrue(changed);
        assertTrue(collection1.containsAll(Arrays.asList("2", "3")));
        assertTrue(collection2.containsAll(Arrays.asList("3", "4")));
        assertFalse(collection1.contains("1"));
        assertFalse(collection2.contains("5"));
    }

    @Test
    public void testRetainAllWithNullCollection() {
        boolean changed = compositeCollection.retainAll(null);
        assertFalse(changed);
        assertTrue(collection1.containsAll(Arrays.asList("1", "2", "3")));
        assertTrue(collection2.containsAll(Arrays.asList("3", "4", "5")));
    }
}
