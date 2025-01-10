
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        two.add("2");
        c.addComposited(one, two);
        assertFalse(c.removeAll(new ArrayList<>()));
        assertEquals(2, c.size());
    }

    @Test
    public void testRemoveAllWithNonEmptyCollection() {
        setUp();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        assertTrue(c.removeAll(Arrays.asList("1", "2")));
        assertTrue(c.isEmpty());
    }

    @Test
    public void testRemoveAllWithPartialMatch() {
        setUp();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        assertTrue(c.removeAll(Arrays.asList("1")));
        assertEquals(1, c.size());
        assertFalse(c.contains("1"));
        assertTrue(c.contains("2"));
    }

    @Test
    public void testRemoveAllWithNoMatch() {
        setUp();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        assertFalse(c.removeAll(Arrays.asList("3")));
        assertEquals(2, c.size());
    }
}
