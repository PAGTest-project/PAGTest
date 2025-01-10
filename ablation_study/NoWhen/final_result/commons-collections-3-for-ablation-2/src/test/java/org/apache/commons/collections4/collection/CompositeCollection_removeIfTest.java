
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_removeIfTest {

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
    public void testRemoveIf_AllElementsRemoved() {
        Predicate<String> filter = s -> true;
        assertTrue(c.removeIf(filter));
        assertEquals(0, c.size());
    }

    @Test
    public void testRemoveIf_NoElementsRemoved() {
        Predicate<String> filter = s -> false;
        assertFalse(c.removeIf(filter));
        assertEquals(4, c.size());
    }

    @Test
    public void testRemoveIf_SomeElementsRemoved() {
        Predicate<String> filter = s -> s.equals("a") || s.equals("c");
        assertTrue(c.removeIf(filter));
        assertEquals(2, c.size());
        assertFalse(c.contains("a"));
        assertFalse(c.contains("c"));
    }

    @Test
    public void testRemoveIf_NullFilter() {
        assertFalse(c.removeIf(null));
        assertEquals(4, c.size());
    }
}
