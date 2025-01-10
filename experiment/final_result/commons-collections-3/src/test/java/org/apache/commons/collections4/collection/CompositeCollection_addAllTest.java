
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
        two = new ArrayList<>(Arrays.asList("3", "4"));
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

        c.addComposited(one);
        assertTrue(c.addAll(two));
        assertEquals(4, c.size());
        assertTrue(c.contains("1"));
        assertTrue(c.contains("2"));
        assertTrue(c.contains("3"));
        assertTrue(c.contains("4"));
    }

    @Test
    public void testAddAllWithoutMutator() {
        c.addComposited(one);
        assertThrows(UnsupportedOperationException.class, () -> c.addAll(two));
    }
}
