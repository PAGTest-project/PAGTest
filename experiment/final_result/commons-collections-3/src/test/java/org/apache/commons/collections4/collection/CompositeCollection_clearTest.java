
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_clearTest {

    private CompositeCollection<String> compositeCollection;
    private Collection<String> collection1;
    private Collection<String> collection2;

    @BeforeEach
    public void setUp() {
        compositeCollection = new CompositeCollection<>();
        collection1 = new ArrayList<>();
        collection2 = new ArrayList<>();
        compositeCollection.addComposited(collection1, collection2);
    }

    @Test
    public void testClear() {
        collection1.add("Element1");
        collection2.add("Element2");

        compositeCollection.clear();

        assertTrue(collection1.isEmpty());
        assertTrue(collection2.isEmpty());
        assertTrue(compositeCollection.isEmpty());
    }
}
