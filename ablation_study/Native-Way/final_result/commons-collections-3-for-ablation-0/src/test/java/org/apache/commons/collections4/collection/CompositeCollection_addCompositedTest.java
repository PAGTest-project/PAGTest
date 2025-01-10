
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_addCompositedTest {
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
    public void testAddCompositedNonNullCollection() {
        one.add("1");
        c.addComposited(one);
        assertEquals(1, c.size());
        assertTrue(c.contains("1"));
    }

    @Test
    public void testAddCompositedNullCollection() {
        c.addComposited((Collection<String>) null);
        assertEquals(0, c.size());
    }

    @Test
    public void testAddCompositedMultipleCollections() {
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        assertEquals(2, c.size());
        assertTrue(c.contains("1"));
        assertTrue(c.contains("2"));
    }

    @Test
    public void testAddCompositedEmptyCollections() {
        c.addComposited(one, two);
        assertEquals(0, c.size());
    }

    @Test
    public void testAddCompositedMixedCollections() {
        one.add("1");
        c.addComposited(one, null);
        assertEquals(1, c.size());
        assertTrue(c.contains("1"));
    }
}
