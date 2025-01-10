
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeSet_isEmptyTest {

    private CompositeSet<String> compositeSet;
    private Set<String> set1;
    private Set<String> set2;

    @BeforeEach
    public void setUp() {
        set1 = new HashSet<>();
        set2 = new HashSet<>();
        compositeSet = new CompositeSet<>(set1, set2);
    }

    @Test
    public void testIsEmpty_AllSetsEmpty() {
        assertTrue(compositeSet.isEmpty());
    }

    @Test
    public void testIsEmpty_OneSetNotEmpty() {
        set1.add("element");
        assertFalse(compositeSet.isEmpty());
    }

    @Test
    public void testIsEmpty_AllSetsNotEmpty() {
        set1.add("element1");
        set2.add("element2");
        assertFalse(compositeSet.isEmpty());
    }

    @Test
    public void testIsEmpty_AfterClear() {
        set1.add("element1");
        set2.add("element2");
        compositeSet.clear();
        assertTrue(compositeSet.isEmpty());
    }

    @Test
    public void testIsEmpty_AfterRemove() {
        set1.add("element1");
        set2.add("element2");
        compositeSet.remove("element1");
        compositeSet.remove("element2");
        assertTrue(compositeSet.isEmpty());
    }
}
