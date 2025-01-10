
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.set.CompositeSet.SetMutator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeSet_sizeTest {

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
    public void testSizeWithEmptySets() {
        compositeSet.addComposited(set1, set2);
        assertEquals(0, compositeSet.size());
    }

    @Test
    public void testSizeWithNonEmptySets() {
        set1.add("A");
        set1.add("B");
        set2.add("C");
        compositeSet.addComposited(set1, set2);
        assertEquals(3, compositeSet.size());
    }

    @Test
    public void testSizeWithDuplicateElements() {
        set1.add("A");
        set1.add("B");
        set2.add("B");
        set2.add("C");
        compositeSet.setMutator(new SetMutator<String>() {
            @Override
            public void resolveCollision(CompositeSet<String> comp, Set<String> existing, Set<String> added, String item) {
                // Do nothing, allow duplicates
            }
        });
        compositeSet.addComposited(set1, set2);
        assertEquals(3, compositeSet.size());
    }

    @Test
    public void testSizeAfterRemovingElements() {
        set1.add("A");
        set1.add("B");
        set2.add("C");
        compositeSet.addComposited(set1, set2);
        set1.remove("A");
        assertEquals(2, compositeSet.size());
    }

    @Test
    public void testSizeAfterAddingElements() {
        set1.add("A");
        set1.add("B");
        compositeSet.addComposited(set1, set2);
        set2.add("C");
        assertEquals(3, compositeSet.size());
    }

    @Test
    public void testSizeWithEmptyCompositeSet() {
        assertEquals(0, compositeSet.size());
    }

    @Test
    public void testSizeWithSingleSet() {
        set1.add("A");
        compositeSet.addComposited(set1);
        assertEquals(1, compositeSet.size());
    }

    @Test
    public void testSizeWithMultipleSets() {
        set1.add("A");
        set2.add("B");
        compositeSet.addComposited(set1, set2);
        assertEquals(2, compositeSet.size());
    }

    @Test
    public void testSizeWithNullSet() {
        compositeSet.addComposited((Set<String>) null);
        assertEquals(0, compositeSet.size());
    }

    @Test
    public void testSizeWithEmptySet() {
        compositeSet.addComposited(set1);
        assertEquals(0, compositeSet.size());
    }
}
