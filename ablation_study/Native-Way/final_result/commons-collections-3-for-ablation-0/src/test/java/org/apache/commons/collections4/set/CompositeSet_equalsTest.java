
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeSet_equalsTest {

    private CompositeSet<String> compositeSet;
    private Set<String> set1;
    private Set<String> set2;

    @BeforeEach
    public void setUp() {
        set1 = new HashSet<>();
        set1.add("1");
        set1.add("2");

        set2 = new HashSet<>();
        set2.add("3");
        set2.add("4");

        compositeSet = new CompositeSet<>(set1, set2);
    }

    @Test
    public void testEquals_SameSets() {
        CompositeSet<String> otherCompositeSet = new CompositeSet<>(set1, set2);
        assertTrue(compositeSet.equals(otherCompositeSet));
    }

    @Test
    public void testEquals_DifferentSets() {
        Set<String> differentSet1 = new HashSet<>();
        differentSet1.add("5");
        differentSet1.add("6");

        Set<String> differentSet2 = new HashSet<>();
        differentSet2.add("7");
        differentSet2.add("8");

        CompositeSet<String> otherCompositeSet = new CompositeSet<>(differentSet1, differentSet2);
        assertFalse(compositeSet.equals(otherCompositeSet));
    }

    @Test
    public void testEquals_DifferentSize() {
        Set<String> differentSet1 = new HashSet<>();
        differentSet1.add("1");
        differentSet1.add("2");
        differentSet1.add("3");

        CompositeSet<String> otherCompositeSet = new CompositeSet<>(differentSet1);
        assertFalse(compositeSet.equals(otherCompositeSet));
    }

    @Test
    public void testEquals_Null() {
        assertFalse(compositeSet.equals(null));
    }

    @Test
    public void testEquals_DifferentType() {
        assertFalse(compositeSet.equals("Not a Set"));
    }

    @Test
    public void testEquals_EmptySets() {
        CompositeSet<String> emptyCompositeSet = new CompositeSet<>();
        CompositeSet<String> otherEmptyCompositeSet = new CompositeSet<>();
        assertTrue(emptyCompositeSet.equals(otherEmptyCompositeSet));
    }
}
