
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testContainsAllWithEmptyComposite() {
        setUpTest();
        Collection<String> testCollection = new ArrayList<>(Arrays.asList("a", "b", "c"));
        assertFalse(c.containsAll(testCollection));
    }

    @Test
    public void testContainsAllWithNonEmptyComposite() {
        setUpTest();
        one.add("a");
        one.add("b");
        two.add("c");
        c.addComposited(one, two);

        Collection<String> testCollection = new ArrayList<>(Arrays.asList("a", "b", "c"));
        assertTrue(c.containsAll(testCollection));
    }

    @Test
    public void testContainsAllWithPartialMatch() {
        setUpTest();
        one.add("a");
        one.add("b");
        c.addComposited(one);

        Collection<String> testCollection = new ArrayList<>(Arrays.asList("a", "b", "c"));
        assertFalse(c.containsAll(testCollection));
    }

    @Test
    public void testContainsAllWithNullCollection() {
        setUpTest();
        assertFalse(c.containsAll(null));
    }

    @Test
    public void testContainsAllWithEmptyCollection() {
        setUpTest();
        assertTrue(c.containsAll(new ArrayList<>()));
    }
}
