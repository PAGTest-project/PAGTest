
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_isEmptyTest {

    private CompositeCollection<String> compositeCollection;
    private List<String> collection1;
    private List<String> collection2;

    @BeforeEach
    public void setUp() {
        compositeCollection = new CompositeCollection<>();
        collection1 = new ArrayList<>();
        collection2 = new ArrayList<>();
    }

    @Test
    public void testIsEmpty_AllCollectionsEmpty() {
        compositeCollection.addComposited(collection1, collection2);
        assertTrue(compositeCollection.isEmpty());
    }

    @Test
    public void testIsEmpty_OneCollectionNotEmpty() {
        collection1.add("element");
        compositeCollection.addComposited(collection1, collection2);
        assertFalse(compositeCollection.isEmpty());
    }
}
