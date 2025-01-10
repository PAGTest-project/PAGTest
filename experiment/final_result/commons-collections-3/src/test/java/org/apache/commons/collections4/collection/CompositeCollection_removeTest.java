
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_removeTest {

    private CompositeCollection<String> compositeCollection;
    private Collection<String> collection1;
    private Collection<String> collection2;
    private CompositeCollection.CollectionMutator<String> mockMutator;

    @BeforeEach
    public void setUp() {
        compositeCollection = new CompositeCollection<>();
        collection1 = new ArrayList<>();
        collection2 = new ArrayList<>();
        mockMutator = new CompositeCollection.CollectionMutator<String>() {
            @Override
            public boolean add(CompositeCollection<String> composite, List<Collection<String>> collections, String obj) {
                return false;
            }

            @Override
            public boolean addAll(CompositeCollection<String> composite, List<Collection<String>> collections, Collection<? extends String> coll) {
                return false;
            }

            @Override
            public boolean remove(CompositeCollection<String> composite, List<Collection<String>> collections, Object obj) {
                return collections.get(0).remove(obj);
            }
        };
        compositeCollection.setMutator(mockMutator);
        compositeCollection.addComposited(collection1);
        compositeCollection.addComposited(collection2);
    }

    @Test
    public void testRemoveWithMutator() {
        collection1.add("element1");
        collection2.add("element2");

        assertTrue(compositeCollection.remove("element1"));
        assertFalse(collection1.contains("element1"));
        assertTrue(collection2.contains("element2"));
    }

    @Test
    public void testRemoveWithoutMutator() {
        compositeCollection.setMutator(null);
        assertThrows(UnsupportedOperationException.class, () -> {
            compositeCollection.remove("element1");
        });
    }
}
