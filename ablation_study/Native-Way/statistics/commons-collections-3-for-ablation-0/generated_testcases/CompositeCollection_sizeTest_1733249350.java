
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_sizeTest {
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
    public void testSizeEmpty() {
        assertEquals(0, c.size());
    }

    @Test
    public void testSizeWithOneCollection() {
        one.add("1");
        c.addComposited(one);
        assertEquals(1, c.size());
    }

    @Test
    public void testSizeWithMultipleCollections() {
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        assertEquals(2, c.size());
    }

    @Test
    public void testSizeAfterClear() {
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        c.clear();
        assertEquals(0, c.size());
    }

    @Test
    public void testSizeAfterAddAndRemove() {
        one.add("1");
        c.addComposited(one);
        assertEquals(1, c.size());
        c.remove("1");
        assertEquals(0, c.size());
    }
}
