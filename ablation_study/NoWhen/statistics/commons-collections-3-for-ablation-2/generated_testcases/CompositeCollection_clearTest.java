
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_clearTest {
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
    public void testClear() {
        one.add("a");
        one.add("b");
        two.add("c");
        c.addComposited(one, two);

        c.clear();

        assertTrue(c.isEmpty());
        assertEquals(0, c.size());
    }

    @Test
    public void testClearWithEmptyComposite() {
        c.clear();

        assertTrue(c.isEmpty());
        assertEquals(0, c.size());
    }

    @Test
    public void testClearWithSingleCollection() {
        one.add("a");
        c.addComposited(one);

        c.clear();

        assertTrue(c.isEmpty());
        assertEquals(0, c.size());
    }

    @Test
    public void testClearWithMultipleCollections() {
        one.add("a");
        one.add("b");
        two.add("c");
        c.addComposited(one, two);

        c.clear();

        assertTrue(c.isEmpty());
        assertEquals(0, c.size());
    }
}
