
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_containsAllTest {
    private CompositeCollection<String> c;
    private Collection<String> one;
    private Collection<String> two;

    @BeforeEach
    protected void setUpTest() {
        c = new CompositeCollection<>();
        one = new HashSet<>();
        two = new HashSet<>();
    }

    @Test
    public void testContainsAllWithNullCollection() {
        assertFalse(c.containsAll(null));
    }

    @Test
    public void testContainsAllWithEmptyCollection() {
        assertTrue(c.containsAll(new ArrayList<>()));
    }

    @Test
    public void testContainsAllWithSingleCollection() {
        one.add("foo");
        c.addComposited(one);
        assertTrue(c.containsAll(Arrays.asList("foo")));
        assertFalse(c.containsAll(Arrays.asList("bar")));
    }

    @Test
    public void testContainsAllWithMultipleCollections() {
        one.add("foo");
        two.add("bar");
        c.addComposited(one, two);
        assertTrue(c.containsAll(Arrays.asList("foo", "bar")));
        assertFalse(c.containsAll(Arrays.asList("foo", "baz")));
    }

    @Test
    public void testContainsAllWithPartialMatch() {
        one.add("foo");
        two.add("bar");
        c.addComposited(one, two);
        assertFalse(c.containsAll(Arrays.asList("foo", "baz")));
    }

    @Test
    public void testContainsAllWithEmptyComposite() {
        assertFalse(c.containsAll(Arrays.asList("foo")));
    }
}
