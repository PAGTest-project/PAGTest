
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
    protected void setUpTest() {
        c = new CompositeCollection<>();
        one = new HashSet<>(Arrays.asList("a", "b", "c"));
        two = new HashSet<>(Arrays.asList("d", "e", "f"));
    }

    @Test
    public void testIteratorWithEmptyComposite() {
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
        c.addComposited(one, two);
        Iterator<String> iterator = c.iterator();
        List<String> result = new ArrayList<>();
        iterator.forEachRemaining(result::add);
        assertEquals(new HashSet<>(Arrays.asList("a", "b", "c", "d", "e", "f")), new HashSet<>(result));
    }

    @Test
    public void testIteratorAfterClear() {
        c.addComposited(one);
        c.clear();
        Iterator<String> iterator = c.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorAfterRemoveComposited() {
        c.addComposited(one, two);
        c.removeComposited(one);
        Iterator<String> iterator = c.iterator();
        List<String> result = new ArrayList<>();
        iterator.forEachRemaining(result::add);
        assertEquals(two, new HashSet<>(result));
    }

    @Test
    public void testIteratorAfterAddElement() {
        c.addComposited(one);
        c.add("g");
        Iterator<String> iterator = c.iterator();
        List<String> result = new ArrayList<>();
        iterator.forEachRemaining(result::add);
        assertTrue(result.contains("g"));
    }
}
