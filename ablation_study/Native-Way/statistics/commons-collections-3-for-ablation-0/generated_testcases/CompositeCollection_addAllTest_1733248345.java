
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_addAllTest {
    private CompositeCollection<String> c;
    private Collection<String> one;
    private Collection<String> two;

    @BeforeEach
    public void setUp() {
        c = new CompositeCollection<>();
        one = new HashSet<>();
        two = new HashSet<>();
    }

    @Test
    public void testAddAllWithMutator() {
        c.setMutator(new CompositeCollection.CollectionMutator<String>() {
            @Override
            public boolean add(CompositeCollection<String> composite, List<Collection<String>> collections, String obj) {
                return false;
            }

            @Override
            public boolean addAll(CompositeCollection<String> composite, List<Collection<String>> collections, Collection<? extends String> coll) {
                collections.get(0).addAll(coll);
                return true;
            }

            @Override
            public boolean remove(CompositeCollection<String> composite, List<Collection<String>> collections, Object obj) {
                return false;
            }
        });

        one.add("1");
        two.add("2");
        c.addComposited(one);

        Collection<String> newColl = new ArrayList<>();
        newColl.add("3");
        newColl.add("4");

        assertTrue(c.addAll(newColl));
        assertEquals(3, one.size());
        assertTrue(one.contains("3"));
        assertTrue(one.contains("4"));
    }

    @Test
    public void testAddAllWithoutMutator() {
        one.add("1");
        two.add("2");
        c.addComposited(one);

        Collection<String> newColl = new ArrayList<>();
        newColl.add("3");
        newColl.add("4");

        assertThrows(UnsupportedOperationException.class, () -> c.addAll(newColl));
    }
}
