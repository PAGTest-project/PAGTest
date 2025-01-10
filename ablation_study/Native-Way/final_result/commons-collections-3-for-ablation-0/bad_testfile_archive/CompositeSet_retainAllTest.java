
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeSet_retainAllTest {

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
    public void testRetainAll_NoChange() {
        Set<String> retainSet = new HashSet<>();
        retainSet.add("5");
        retainSet.add("6");

        boolean changed = compositeSet.retainAll(retainSet);

        assertFalse(changed);
        assertEquals(4, compositeSet.size());
        assertTrue(compositeSet.contains("1"));
        assertTrue(compositeSet.contains("2"));
        assertTrue(compositeSet.contains("3"));
        assertTrue(compositeSet.contains("4"));
    }

    @Test
    public void testRetainAll_WithChange() {
        Set<String> retainSet = new HashSet<>();
        retainSet.add("2");
        retainSet.add("3");

        boolean changed = compositeSet.retainAll(retainSet);

        assertTrue(changed);
        assertEquals(2, compositeSet.size());
        assertTrue(compositeSet.contains("2"));
        assertTrue(compositeSet.contains("3"));
        assertFalse(compositeSet.contains("1"));
        assertFalse(compositeSet.contains("4"));
    }

    @Test
    public void testRetainAll_EmptyCollection() {
        Set<String> retainSet = new HashSet<>();

        boolean changed = compositeSet.retainAll(retainSet);

        assertTrue(changed);
        assertEquals(0, compositeSet.size());
    }

    @Test
    public void testRetainAll_NullCollection() {
        boolean changed = compositeSet.retainAll(null);

        assertFalse(changed);
        assertEquals(4, compositeSet.size());
        assertTrue(compositeSet.contains("1"));
        assertTrue(compositeSet.contains("2"));
        assertTrue(compositeSet.contains("3"));
        assertTrue(compositeSet.contains("4"));
    }
}
