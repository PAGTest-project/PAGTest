
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_removeTest {
    private CompositeCollection<String> c;
    private Collection<String> one;
    private Collection<String> two;

    @BeforeEach
    protected void setUpTest() {
        c = new CompositeCollection<>();
        one = new HashSet<>();
        two = new HashSet<>();
    }

    @Test
    @SuppressWarnings({ "unchecked", "serial" })
    public void testRemoveMutator() {
        setUpTest();
        c.setMutator(new CompositeCollection.CollectionMutator<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean add(final CompositeCollection<String> composite,
                    final List<Collection<String>> collections, final String obj) {
                for (final Collection<String> collection : collections) {
                    collection.add(obj);
                }
                return true;
            }

            @Override
            public boolean addAll(final CompositeCollection<String> composite,
                    final List<Collection<String>> collections, final Collection<? extends String> coll) {
                for (final Collection<String> collection : collections) {
                    collection.addAll(coll);
                }
                return true;
            }

            @Override
            public boolean remove(final CompositeCollection<String> composite,
                    final List<Collection<String>> collections, final Object obj) {
                for (final Collection<String> collection : collections) {
                    collection.remove(obj);
                }
                return true;
            }
        });

        c.addComposited(one);
        one.add("foo"); // Directly add to the collection
        assertTrue(c.contains("foo"));
        assertTrue(one.contains("foo"));

        assertTrue(c.remove("foo"));
        assertFalse(c.contains("foo"));
        assertFalse(one.contains("foo"));
    }

    @Test
    public void testRemoveWithoutMutator() {
        setUpTest();
        c.addComposited(one);
        one.add("foo"); // Directly add to the collection
        assertTrue(c.contains("foo"));
        assertTrue(one.contains("foo"));

        assertThrows(UnsupportedOperationException.class, () -> c.remove("foo"));
    }
}
