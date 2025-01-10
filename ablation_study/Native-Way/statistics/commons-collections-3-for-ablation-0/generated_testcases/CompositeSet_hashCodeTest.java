
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeSet_hashCodeTest {

    private CompositeSet<String> compositeSet;

    @BeforeEach
    public void setUp() {
        compositeSet = new CompositeSet<>();
    }

    @Test
    public void testHashCodeWithEmptySet() {
        assertEquals(0, compositeSet.hashCode());
    }

    @Test
    public void testHashCodeWithSingleElement() {
        Set<String> set = new HashSet<>();
        set.add("element");
        compositeSet.addComposited(set);
        assertEquals("element".hashCode(), compositeSet.hashCode());
    }

    @Test
    public void testHashCodeWithMultipleElements() {
        Set<String> set1 = new HashSet<>();
        set1.add("element1");
        Set<String> set2 = new HashSet<>();
        set2.add("element2");
        compositeSet.addComposited(set1, set2);
        int expectedHashCode = "element1".hashCode() + "element2".hashCode();
        assertEquals(expectedHashCode, compositeSet.hashCode());
    }

    @Test
    public void testHashCodeWithNullElement() {
        Set<String> set = new HashSet<>();
        set.add(null);
        compositeSet.addComposited(set);
        assertEquals(0, compositeSet.hashCode());
    }

    @Test
    public void testHashCodeAfterAddingAndRemovingElements() {
        Set<String> set = new HashSet<>();
        set.add("element1");
        set.add("element2");
        compositeSet.addComposited(set);
        int initialHashCode = compositeSet.hashCode();

        set.remove("element1");
        int expectedHashCodeAfterRemove = "element2".hashCode();
        assertEquals(expectedHashCodeAfterRemove, compositeSet.hashCode());

        set.add("element3");
        int expectedHashCodeAfterAdd = "element2".hashCode() + "element3".hashCode();
        assertEquals(expectedHashCodeAfterAdd, compositeSet.hashCode());
    }

    @Test
    public void testHashCodeAfterClear() {
        Set<String> set = new HashSet<>();
        set.add("element1");
        set.add("element2");
        compositeSet.addComposited(set);
        compositeSet.clear();
        assertEquals(0, compositeSet.hashCode());
    }
}
