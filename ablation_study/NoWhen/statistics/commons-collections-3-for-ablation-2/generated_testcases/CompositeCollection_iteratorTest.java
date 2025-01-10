
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_iteratorTest {

    private CompositeCollection<String> c;
    private Collection<String> one;
    private Collection<String> two;

    @BeforeEach
    public void setUp() {
        c = new CompositeCollection<>();
        one = new HashSet<>(Arrays.asList("a", "b"));
        two = new HashSet<>(Arrays.asList("c", "d"));
    }

    @Test
    public void testIteratorWithEmptyCompositeCollection() {
        Iterator<String> iterator = c.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorWithSingleCollection() {
        c.addComposited(one);
        Iterator<String> iterator = c.iterator();
        List<String> result = new ArrayList<>();
        iterator.forEachRemaining(result::add);
        assertEquals(one, new HashSet<>(result));
    }

    @Test
    public void testIteratorWithMultipleCollections() {
        c.addComposited(one);
        c.addComposited(two);
        Iterator<String> iterator = c.iterator();
        List<String> result = new ArrayList<>();
        iterator.forEachRemaining(result::add);
        assertEquals(new HashSet<>(Arrays.asList("a", "b", "c", "d")), new HashSet<>(result));
    }

    @Test
    public void testIteratorAfterRemovingCollection() {
        c.addComposited(one);
        c.addComposited(two);
        c.removeComposited(two);
        Iterator<String> iterator = c.iterator();
        List<String> result = new ArrayList<>();
        iterator.forEachRemaining(result::add);
        assertEquals(one, new HashSet<>(result));
    }
}
