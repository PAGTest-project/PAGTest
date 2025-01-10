
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_containsTest {
    private CompositeCollection<String> c;
    private Collection<String> one;
    private Collection<String> two;

    @BeforeEach
    public void setUp() {
        c = new CompositeCollection<>();
        one = new HashSet<>();
        two = new HashSet<>();
    }

    @Test
    public void testContainsWithEmptyComposite() {
        assertFalse(c.contains("a"));
    }

    @Test
    public void testContainsWithSingleCollection() {
        one.add("a");
        c.addComposited(one);
        assertTrue(c.contains("a"));
        assertFalse(c.contains("b"));
    }

    @Test
    public void testContainsWithMultipleCollections() {
        one.add("a");
        two.add("b");
        c.addComposited(one, two);
        assertTrue(c.contains("a"));
        assertTrue(c.contains("b"));
        assertFalse(c.contains("c"));
    }

    @Test
    public void testContainsAfterRemoveComposited() {
        one.add("a");
        two.add("b");
        c.addComposited(one, two);
        c.removeComposited(one);
        assertFalse(c.contains("a"));
        assertTrue(c.contains("b"));
    }

    @Test
    public void testContainsWithEmptyCollection() {
        one.add("a");
        c.addComposited(one);
        one.clear();
        assertFalse(c.contains("a"));
    }
}
