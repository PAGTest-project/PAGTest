
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_addTest {

    private CompositeCollection<String> compositeCollection;
    private CompositeCollection.CollectionMutator<String> mutator;

    @BeforeEach
    public void setUp() {
        compositeCollection = new CompositeCollection<>();
        mutator = new CompositeCollection.CollectionMutator<String>() {
            @Override
            public boolean add(CompositeCollection<String> composite, List<Collection<String>> collections, String obj) {
                for (Collection<String> collection : collections) {
                    collection.add(obj);
                }
                return true;
            }

            @Override
            public boolean addAll(CompositeCollection<String> composite, List<Collection<String>> collections, Collection<? extends String> coll) {
                for (Collection<String> collection : collections) {
                    collection.addAll(coll);
                }
                return true;
            }

            @Override
            public boolean remove(CompositeCollection<String> composite, List<Collection<String>> collections, Object obj) {
                for (Collection<String> collection : collections) {
                    collection.remove(obj);
                }
                return true;
            }
        };
    }

    @Test
    public void testAddWithMutator() {
        compositeCollection.setMutator(mutator);
        compositeCollection.add("test");
        assertTrue(compositeCollection.contains("test"));
    }

    @Test
    public void testAddWithoutMutator() {
        assertThrows(UnsupportedOperationException.class, () -> {
            compositeCollection.add("test");
        });
    }
}
