
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_removeAllTest {
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
    public void testRemoveAllWithEmptyCollection() {
        setUp();
        one.add("1");
        one.add("2");
        c.addComposited(one);
        assertFalse(c.removeAll(new ArrayList<>()));
        assertTrue(c.contains("1"));
        assertTrue(c.contains("2"));
    }

    @Test
    public void testRemoveAllWithNonEmptyCollection() {
        setUp();
        one.add("1");
        one.add("2");
        two.add("2");
        c.addComposited(one);
        assertTrue(c.removeAll(two));
        assertFalse(c.contains("2"));
        assertTrue(c.contains("1"));
    }

    @Test
    public void testRemoveAllWithAllElements() {
        setUp();
        one.add("1");
        one.add("2");
        two.add("1");
        two.add("2");
        c.addComposited(one);
        assertTrue(c.removeAll(two));
        assertFalse(c.contains("1"));
        assertFalse(c.contains("2"));
    }

    @Test
    public void testRemoveAllWithNoMatchingElements() {
        setUp();
        one.add("1");
        one.add("2");
        two.add("3");
        c.addComposited(one);
        assertFalse(c.removeAll(two));
        assertTrue(c.contains("1"));
        assertTrue(c.contains("2"));
    }
}
