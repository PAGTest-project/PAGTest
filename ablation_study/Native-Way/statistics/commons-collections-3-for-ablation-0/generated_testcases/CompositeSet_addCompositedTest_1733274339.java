
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections4.set.CompositeSet.SetMutator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeSet_addCompositedTest {

    private CompositeSet<String> compositeSet;
    private Set<String> set1;
    private Set<String> set2;

    @BeforeEach
    public void setUp() {
        compositeSet = new CompositeSet<>();
        set1 = new HashSet<>();
        set2 = new HashSet<>();
        set1.add("1");
        set1.add("2");
        set2.add("3");
        set2.add("4");
    }

    @Test
    public void testAddCompositedNoCollision() {
        compositeSet.addComposited(set1);
        compositeSet.addComposited(set2);
        assertEquals(4, compositeSet.size());
    }

    @Test
    public void testAddCompositedWithCollisionNoMutator() {
        set2.add("2"); // Introduce collision
        assertThrows(UnsupportedOperationException.class, () -> {
            compositeSet.addComposited(set1);
            compositeSet.addComposited(set2);
        });
    }

    @Test
    public void testAddCompositedWithCollisionAndMutator() {
        set2.add("2"); // Introduce collision
        compositeSet.setMutator(new SetMutator<String>() {
            @Override
            public boolean add(CompositeSet<String> composite, List<Set<String>> sets, String obj) {
                return true;
            }

            @Override
            public boolean addAll(CompositeSet<String> composite, List<Set<String>> sets, Collection<? extends String> coll) {
                return true;
            }

            @Override
            public void resolveCollision(CompositeSet<String> comp, Set<String> existing, Set<String> added, Collection<String> intersects) {
                added.removeAll(intersects);
            }
        });
        compositeSet.addComposited(set1);
        compositeSet.addComposited(set2);
        assertEquals(4, compositeSet.size());
    }

    @Test
    public void testAddCompositedNullSet() {
        compositeSet.addComposited(null);
        assertEquals(0, compositeSet.size());
    }

    @Test
    public void testAddCompositedIllegalEntryUnresolved() {
        set2.add("2"); // Introduce collision
        compositeSet.setMutator(new SetMutator<String>() {
            @Override
            public boolean add(CompositeSet<String> composite, List<Set<String>> sets, String obj) {
                return true;
            }

            @Override
            public boolean addAll(CompositeSet<String> composite, List<Set<String>> sets, Collection<? extends String> coll) {
                return true;
            }

            @Override
            public void resolveCollision(CompositeSet<String> comp, Set<String> existing, Set<String> added, Collection<String> intersects) {
                // Do nothing, let the collision unresolved
            }
        });
        assertThrows(IllegalArgumentException.class, () -> {
            compositeSet.addComposited(set1);
            compositeSet.addComposited(set2);
        });
    }
}
