
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections4.set.CompositeSet.SetMutator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeSet_containsAllTest {

    private CompositeSet<Integer> compositeSet;
    private Set<Integer> set1;
    private Set<Integer> set2;

    @BeforeEach
    public void setUp() {
        set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2 = new HashSet<>();
        set2.add(3);
        set2.add(4);
        set2.add(5);

        compositeSet = new CompositeSet<>(set1, set2);
        compositeSet.setMutator(new SetMutator<Integer>() {
            @Override
            public void resolveCollision(CompositeSet<Integer> comp, Set<Integer> existing, Set<Integer> added, Collection<Integer> intersecting) {
                // Do nothing, allow the collision
            }
        });
    }

    @Test
    public void testContainsAll_NullCollection() {
        assertFalse(compositeSet.containsAll(null));
    }

    @Test
    public void testContainsAll_EmptyCollection() {
        assertTrue(compositeSet.containsAll(new HashSet<>()));
    }

    @Test
    public void testContainsAll_AllElementsPresent() {
        Set<Integer> testSet = new HashSet<>();
        testSet.add(1);
        testSet.add(4);
        testSet.add(5);

        assertTrue(compositeSet.containsAll(testSet));
    }

    @Test
    public void testContainsAll_SomeElementsMissing() {
        Set<Integer> testSet = new HashSet<>();
        testSet.add(1);
        testSet.add(4);
        testSet.add(6);

        assertFalse(compositeSet.containsAll(testSet));
    }

    @Test
    public void testContainsAll_AllElementsMissing() {
        Set<Integer> testSet = new HashSet<>();
        testSet.add(6);
        testSet.add(7);
        testSet.add(8);

        assertFalse(compositeSet.containsAll(testSet));
    }
}
