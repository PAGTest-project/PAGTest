
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_addTest {

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
    public void testAddWithMutator() {
        setUpTest();
        c.setMutator(new CompositeCollection.CollectionMutator<String>() {
            @Override
            public boolean add(CompositeCollection<String> composite, List<Collection<String>> collections, String obj) {
                return collections.get(0).add(obj);
            }

            @Override
            public boolean addAll(CompositeCollection<String> composite, List<Collection<String>> collections, Collection<? extends String> coll) {
                return false;
            }

            @Override
            public boolean remove(CompositeCollection<String> composite, List<Collection<String>> collections, Object obj) {
                return false;
            }
        });
        c.addComposited(one);
        assertTrue(c.add("test"));
        assertTrue(one.contains("test"));
    }

    @Test
    public void testAddWithoutMutator() {
        setUpTest();
        assertThrows(UnsupportedOperationException.class, () -> c.add("test"));
    }

    @Test
    public void testAddIncreasesSize() {
        setUpTest();
        c.setMutator(new CompositeCollection.CollectionMutator<String>() {
            @Override
            public boolean add(CompositeCollection<String> composite, List<Collection<String>> collections, String obj) {
                return collections.get(0).add(obj);
            }

            @Override
            public boolean addAll(CompositeCollection<String> composite, List<Collection<String>> collections, Collection<? extends String> coll) {
                return false;
            }

            @Override
            public boolean remove(CompositeCollection<String> composite, List<Collection<String>> collections, Object obj) {
                return false;
            }
        });
        c.addComposited(one);
        c.add("test");
        assertEquals(1, c.size());
    }
}
