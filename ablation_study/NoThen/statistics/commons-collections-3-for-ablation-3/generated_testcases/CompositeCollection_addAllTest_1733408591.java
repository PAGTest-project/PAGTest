
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_addAllTest {

    private CompositeCollection<String> c;
    private Collection<String> one;
    private Collection<String> two;

    @BeforeEach
    public void setUp() {
        c = new CompositeCollection<>();
        one = new ArrayList<>(Arrays.asList("1", "2"));
        two = new HashSet<>(Arrays.asList("3", "4"));
    }

    @Test
    public void testAddAllWithMutator() {
        CompositeCollection.CollectionMutator<String> mutator = new CompositeCollection.CollectionMutator<String>() {
            @Override
            public boolean add(CompositeCollection<String> composite, List<Collection<String>> collections, String obj) {
                for (Collection<String> coll : collections) {
                    coll.add(obj);
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
                for (Collection<String> coll : collections) {
                    coll.remove(obj);
                }
                return true;
            }
        };

        c.setMutator(mutator);
        c.addComposited(one, two);

        Collection<String> newElements = Arrays.asList("5", "6");
        assertTrue(c.addAll(newElements));
        assertTrue(one.containsAll(newElements));
        assertTrue(two.containsAll(newElements));
    }

    @Test
    public void testAddAllWithoutMutator() {
        assertThrows(UnsupportedOperationException.class, () -> {
            c.addAll(Arrays.asList("5", "6"));
        });
    }

    @Test
    public void testAddAllWithEmptyCollection() {
        CompositeCollection.CollectionMutator<String> mutator = new CompositeCollection.CollectionMutator<String>() {
            @Override
            public boolean add(CompositeCollection<String> composite, List<Collection<String>> collections, String obj) {
                for (Collection<String> coll : collections) {
                    coll.add(obj);
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
                for (Collection<String> coll : collections) {
                    coll.remove(obj);
                }
                return true;
            }
        };

        c.setMutator(mutator);
        c.addComposited(one, two);

        Collection<String> emptyCollection = new ArrayList<>();
        assertFalse(c.addAll(emptyCollection));
        assertEquals(4, c.size());
    }
}
