
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_addCompositedTest {
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
    public void testAddCompositedSingleCollection() {
        setUpTest();
        one.add("1");
        c.addComposited(one);
        assertEquals(1, c.size());
        assertTrue(c.contains("1"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAddCompositedNullCollection() {
        setUpTest();
        c.addComposited((Collection<String>) null);
        assertEquals(0, c.size());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAddCompositedMultipleCollections() {
        setUpTest();
        one.add("1");
        two.add("2");
        c.addComposited(new Collection[]{one, two});
        assertEquals(2, c.size());
        assertTrue(c.contains("1"));
        assertTrue(c.contains("2"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAddCompositedArrayOfCollections() {
        setUpTest();
        one.add("1");
        two.add("2");
        Collection<String> three = new HashSet<>();
        three.add("3");
        c.addComposited(new Collection[]{one, two, three});
        assertEquals(3, c.size());
        assertTrue(c.contains("1"));
        assertTrue(c.contains("2"));
        assertTrue(c.contains("3"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAddCompositedWithClear() {
        setUpTest();
        one.add("1");
        two.add("2");
        c.addComposited(new Collection[]{one, two});
        c.clear();
        assertTrue(one.isEmpty());
        assertTrue(two.isEmpty());
        assertTrue(c.isEmpty());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAddCompositedWithRemoveComposited() {
        setUpTest();
        one.add("1");
        two.add("2");
        c.addComposited(new Collection[]{one, two});
        c.removeComposited(one);
        assertEquals(1, c.size());
        assertFalse(c.contains("1"));
        assertTrue(c.contains("2"));
    }
}
