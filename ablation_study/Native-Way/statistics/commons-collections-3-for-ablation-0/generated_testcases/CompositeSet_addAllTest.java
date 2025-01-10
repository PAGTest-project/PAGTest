
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.set.CompositeSet.SetMutator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeSet_addAllTest {

    private CompositeSet<String> compositeSet;

    @BeforeEach
    public void setUp() {
        compositeSet = new CompositeSet<>();
    }

    @Test
    public void testAddAllWithMutator() {
        compositeSet.setMutator(new SetMutator<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean add(CompositeSet<String> composite, List<Set<String>> collections, String obj) {
                return true;
            }

            @Override
            public boolean addAll(CompositeSet<String> composite, List<Set<String>> collections, Collection<? extends String> coll) {
                return true;
            }

            @Override
            public void resolveCollision(CompositeSet<String> comp, Set<String> existing, Set<String> added, Collection<String> intersects) {
                // noop
            }
        });

        Set<String> set = new HashSet<>();
        set.add("A");
        set.add("B");

        assertTrue(compositeSet.addAll(set));
    }

    @Test
    public void testAddAllWithoutMutator() {
        Set<String> set = new HashSet<>();
        set.add("A");
        set.add("B");

        assertThrows(UnsupportedOperationException.class, () -> compositeSet.addAll(set));
    }

    @Test
    public void testAddAllWithEmptyCollection() {
        compositeSet.setMutator(new SetMutator<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean add(CompositeSet<String> composite, List<Set<String>> collections, String obj) {
                return true;
            }

            @Override
            public boolean addAll(CompositeSet<String> composite, List<Set<String>> collections, Collection<? extends String> coll) {
                return false;
            }

            @Override
            public void resolveCollision(CompositeSet<String> comp, Set<String> existing, Set<String> added, Collection<String> intersects) {
                // noop
            }
        });

        Set<String> set = new HashSet<>();
        assertFalse(compositeSet.addAll(set));
    }
}
