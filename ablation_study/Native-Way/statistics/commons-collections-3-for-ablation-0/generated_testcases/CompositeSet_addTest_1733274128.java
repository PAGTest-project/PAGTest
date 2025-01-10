
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeSet_addTest {

    private CompositeSet<String> compositeSet;
    private Set<String> set1;
    private Set<String> set2;

    @BeforeEach
    public void setUp() {
        compositeSet = new CompositeSet<>();
        set1 = new HashSet<>();
        set2 = new HashSet<>();
    }

    @Test
    public void testAddWithSetMutator() {
        compositeSet.setMutator(new CompositeSet.SetMutator<String>() {
            @Override
            public boolean add(CompositeSet<String> comp, List<Set<String>> sets, String obj) {
                sets.get(0).add(obj);
                return true;
            }

            @Override
            public boolean addAll(CompositeSet<String> comp, List<Set<String>> sets, Collection<? extends String> coll) {
                return false;
            }

            @Override
            public boolean remove(CompositeSet<String> comp, List<Set<String>> sets, Object obj) {
                return false;
            }
        });
        compositeSet.addComposited(set1);
        assertTrue(compositeSet.add("element"));
        assertTrue(set1.contains("element"));
    }

    @Test
    public void testAddWithoutSetMutator() {
        compositeSet.addComposited(set1);
        assertThrows(UnsupportedOperationException.class, () -> compositeSet.add("element"));
    }

    @Test
    public void testAddWithNullSetMutator() {
        compositeSet.setMutator(null);
        compositeSet.addComposited(set1);
        assertThrows(UnsupportedOperationException.class, () -> compositeSet.add("element"));
    }

    @Test
    public void testAddWithCollision() {
        set1.add("element");
        set2.add("element");
        compositeSet.addComposited(set1);
        compositeSet.addComposited(set2);
        compositeSet.setMutator(new CompositeSet.SetMutator<String>() {
            @Override
            public boolean add(CompositeSet<String> comp, List<Set<String>> sets, String obj) {
                sets.get(0).add(obj);
                return true;
            }

            @Override
            public boolean addAll(CompositeSet<String> comp, List<Set<String>> sets, Collection<? extends String> coll) {
                return false;
            }

            @Override
            public boolean remove(CompositeSet<String> comp, List<Set<String>> sets, Object obj) {
                return false;
            }
        });
        assertThrows(IllegalArgumentException.class, () -> compositeSet.add("element"));
    }
}
