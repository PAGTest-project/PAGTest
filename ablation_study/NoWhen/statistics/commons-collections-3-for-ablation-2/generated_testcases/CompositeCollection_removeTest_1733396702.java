
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.collections4.CollectionMutator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_removeTest {
    private CompositeCollection<String> c;
    private HashSet<String> one;
    private HashSet<String> two;

    @BeforeEach
    protected void setUpTest() {
        c = new CompositeCollection<>();
        one = new HashSet<>();
        two = new HashSet<>();
    }

    @Test
    public void testRemoveWithMutator() {
        setUpTest();
        one.add("a");
        one.add("b");
        c.addComposited(one);

        CollectionMutator<String> mutator = new CollectionMutator<String>() {
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
                for (Collection<String> collection : collections) {
                    if (collection.remove(obj)) {
                        return true;
                    }
                }
                return false;
            }
        };

        c.setMutator(mutator);
        assertTrue(c.remove("a"));
        assertFalse(c.contains("a"));
        assertEquals(1, c.size());
    }

    @Test
    public void testRemoveWithoutMutator() {
        setUpTest();
        one.add("a");
        one.add("b");
        c.addComposited(one);

        assertThrows(UnsupportedOperationException.class, () -> c.remove("a"));
    }
}
