
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
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
    protected void setUpTest() {
        c = new CompositeCollection<>();
        one = new HashSet<>();
        two = new HashSet<>();
    }

    @Test
    public void testRetainAllWithNullCollection() {
        setUpTest();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        assertFalse(c.retainAll(null));
        assertEquals(2, c.size());
    }

    @Test
    public void testRetainAllWithEmptyCollection() {
        setUpTest();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        Collection<String> retainCollection = new ArrayList<>();
        assertTrue(c.retainAll(retainCollection));
        assertTrue(c.isEmpty());
    }

    @Test
    public void testRetainAllWithNonEmptyCollection() {
        setUpTest();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        Collection<String> retainCollection = new ArrayList<>(Arrays.asList("1"));
        assertTrue(c.retainAll(retainCollection));
        assertEquals(1, c.size());
        assertTrue(c.contains("1"));
        assertFalse(c.contains("2"));
    }

    @Test
    public void testRetainAllWithAllElementsToRetain() {
        setUpTest();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        Collection<String> retainCollection = new ArrayList<>(Arrays.asList("1", "2"));
        assertFalse(c.retainAll(retainCollection));
        assertEquals(2, c.size());
        assertTrue(c.contains("1"));
        assertTrue(c.contains("2"));
    }
}
