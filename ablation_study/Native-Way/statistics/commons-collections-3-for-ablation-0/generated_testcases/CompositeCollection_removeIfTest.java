
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
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
    protected void setUpTest() {
        c = new CompositeCollection<>();
        one = new HashSet<>();
        two = new HashSet<>();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testRemoveIf_NullFilter() {
        setUpTest();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        assertFalse(c.removeIf(null));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testRemoveIf_NoChange() {
        setUpTest();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        Predicate<String> filter = s -> s.equals("3");
        assertFalse(c.removeIf(filter));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testRemoveIf_WithChange() {
        setUpTest();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        Predicate<String> filter = s -> s.equals("1");
        assertTrue(c.removeIf(filter));
        assertFalse(one.contains("1"));
        assertTrue(two.contains("2"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testRemoveIf_MultipleCollections() {
        setUpTest();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        Predicate<String> filter = s -> s.equals("2");
        assertTrue(c.removeIf(filter));
        assertTrue(one.contains("1"));
        assertFalse(two.contains("2"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testRemoveIf_AllCollections() {
        setUpTest();
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        Predicate<String> filter = s -> s.equals("1") || s.equals("2");
        assertTrue(c.removeIf(filter));
        assertTrue(one.isEmpty());
        assertTrue(two.isEmpty());
    }
}
