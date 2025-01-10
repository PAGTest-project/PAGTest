
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_addTest {

    private CompositeCollection<String> c;
    private Collection<String> one;
    private Collection<String> two;

    @BeforeEach
    public void setUp() {
        c = new CompositeCollection<>();
        one = new ArrayList<>();
        two = new ArrayList<>();
    }

    @Test
    public void testAddWithoutMutator() {
        assertThrows(UnsupportedOperationException.class, () -> c.add("test"));
    }

    @Test
    public void testAddWithMutator() {
        setUpMutatorTest();
        assertTrue(c.add("test"));
        assertTrue(c.contains("test"));
    }

    @Test
    public void testAddWithCompositedCollections() {
        setUpMutatorTest();
        c.addComposited(one);
        c.addComposited(two);
        assertTrue(c.add("test"));
        assertTrue(one.contains("test") || two.contains("test"));
    }

    private void setUpMutatorTest() {
        c.setMutator(new CompositeCollection.CollectionMutator<String>() {
            @Override
            public boolean add(CompositeCollection<String> composite, List<Collection<String>> collections, String obj) {
                for (Collection<String> coll : collections) {
                    if (coll.add(obj)) {
                        return true;
                    }
                }
                return one.add(obj); // Ensure the object is added to one of the collections
            }

            @Override
            public boolean addAll(CompositeCollection<String> composite, List<Collection<String>> collections, Collection<? extends String> coll) {
                boolean changed = false;
                for (String obj : coll) {
                    changed |= add(composite, collections, obj);
                }
                return changed;
            }

            @Override
            public boolean remove(CompositeCollection<String> composite, List<Collection<String>> collections, Object obj) {
                boolean changed = false;
                for (Collection<String> coll : collections) {
                    changed |= coll.remove(obj);
                }
                return changed;
            }
        });
    }
}
