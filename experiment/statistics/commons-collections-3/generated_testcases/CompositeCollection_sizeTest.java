
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_sizeTest {

    private CompositeCollection<String> compositeCollection;
    private List<Collection<String>> collections;

    @BeforeEach
    public void setUp() {
        compositeCollection = new CompositeCollection<>();
        collections = new ArrayList<>();
    }

    @Test
    public void testSizeWithEmptyCollections() {
        assertEquals(0, compositeCollection.size());
    }

    @Test
    public void testSizeWithNonEmptyCollections() {
        Collection<String> collection1 = new ArrayList<>(List.of("A", "B"));
        Collection<String> collection2 = new ArrayList<>(List.of("C"));
        collections.add(collection1);
        collections.add(collection2);
        compositeCollection.addComposited(collections.toArray(new Collection[0]));

        assertEquals(3, compositeCollection.size());
    }

    @Test
    public void testSizeWithMixedCollections() {
        Collection<String> collection1 = new ArrayList<>(List.of("A", "B"));
        Collection<String> collection2 = new ArrayList<>();
        collections.add(collection1);
        collections.add(collection2);
        compositeCollection.addComposited(collections.toArray(new Collection[0]));

        assertEquals(2, compositeCollection.size());
    }
}
