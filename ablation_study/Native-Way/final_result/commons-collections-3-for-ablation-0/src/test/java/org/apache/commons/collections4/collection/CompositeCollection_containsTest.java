
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
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
    public void testContainsWithSingleCollection() {
        one.add("element1");
        c.addComposited(one);
        assertTrue(c.contains("element1"));
        assertFalse(c.contains("element2"));
    }

    @Test
    public void testContainsWithMultipleCollections() {
        one.add("element1");
        two.add("element2");
        c.addComposited(one, two);
        assertTrue(c.contains("element1"));
        assertTrue(c.contains("element2"));
        assertFalse(c.contains("element3"));
    }

    @Test
    public void testContainsWithEmptyComposite() {
        assertFalse(c.contains("element1"));
    }

    @Test
    public void testContainsWithNullElement() {
        one.add(null);
        c.addComposited(one);
        assertTrue(c.contains(null));
    }

    @Test
    public void testContainsWithMultipleCollectionsAndNull() {
        one.add("element1");
        two.add(null);
        c.addComposited(one, two);
        assertTrue(c.contains("element1"));
        assertTrue(c.contains(null));
        assertFalse(c.contains("element2"));
    }
}
