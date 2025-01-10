
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_retainAllTest {

    private CompositeCollection<String> c;
    private Collection<String> one;
    private Collection<String> two;

    @BeforeEach
    public void setUp() {
        c = new CompositeCollection<>();
        one = new HashSet<>(Arrays.asList("a", "b"));
        two = new HashSet<>(Arrays.asList("c", "d"));
        c.addComposited(one, two);
    }

    @Test
    public void testRetainAllWithMatchingElements() {
        Collection<String> retainCollection = new HashSet<>(Arrays.asList("a", "c"));
        boolean changed = c.retainAll(retainCollection);
        assertTrue(changed);
        assertEquals(2, c.size());
        assertTrue(c.containsAll(retainCollection));
    }

    @Test
    public void testRetainAllWithNoMatchingElements() {
        Collection<String> retainCollection = new HashSet<>(Arrays.asList("e", "f"));
        boolean changed = c.retainAll(retainCollection);
        assertTrue(changed);
        assertEquals(0, c.size());
        assertFalse(c.containsAll(retainCollection));
    }

    @Test
    public void testRetainAllWithNullCollection() {
        boolean changed = c.retainAll(null);
        assertFalse(changed);
        assertEquals(4, c.size());
    }

    @Test
    public void testRetainAllWithEmptyCollection() {
        Collection<String> retainCollection = new ArrayList<>();
        boolean changed = c.retainAll(retainCollection);
        assertTrue(changed);
        assertEquals(0, c.size());
    }

    @Test
    public void testRetainAllWithAllElementsMatching() {
        Collection<String> retainCollection = new HashSet<>(Arrays.asList("a", "b", "c", "d"));
        boolean changed = c.retainAll(retainCollection);
        assertFalse(changed);
        assertEquals(4, c.size());
        assertTrue(c.containsAll(retainCollection));
    }
}
