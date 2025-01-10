
package org.apache.commons.collections4.collection;

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
        one = new HashSet<>();
        two = new HashSet<>();
    }

    @Test
    public void testRetainAllWithNullCollection() {
        setUp();
        assertFalse(c.retainAll(null));
    }

    @Test
    public void testRetainAllWithEmptyCollection() {
        setUp();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        assertTrue(c.retainAll(new ArrayList<>()));
    }

    @Test
    public void testRetainAllWithMatchingElements() {
        setUp();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        Collection<String> retain = new HashSet<>(Arrays.asList("1", "2"));
        assertFalse(c.retainAll(retain));
    }

    @Test
    public void testRetainAllWithNonMatchingElements() {
        setUp();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        Collection<String> retain = new HashSet<>(Arrays.asList("3", "4"));
        assertTrue(c.retainAll(retain));
    }

    @Test
    public void testRetainAllWithPartialMatchingElements() {
        setUp();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        Collection<String> retain = new HashSet<>(Arrays.asList("1", "3"));
        assertTrue(c.retainAll(retain));
    }
}
