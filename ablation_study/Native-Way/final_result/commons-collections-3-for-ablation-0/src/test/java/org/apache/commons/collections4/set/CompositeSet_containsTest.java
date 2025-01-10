
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeSet_containsTest {

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
    public void testContains_ElementInFirstSet() {
        assertTrue(compositeSet.contains("1"));
    }

    @Test
    public void testContains_ElementInSecondSet() {
        assertTrue(compositeSet.contains("3"));
    }

    @Test
    public void testContains_ElementNotInAnySet() {
        assertFalse(compositeSet.contains("5"));
    }

    @Test
    public void testContains_NullElement() {
        assertFalse(compositeSet.contains(null));
    }

    @Test
    public void testContains_EmptyCompositeSet() {
        compositeSet = new CompositeSet<>();
        assertFalse(compositeSet.contains("1"));
    }

    @Test
    public void testContains_ElementInBothSets() {
        set2.add("2");
        assertTrue(compositeSet.contains("2"));
    }
}
