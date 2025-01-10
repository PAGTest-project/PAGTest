
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

public class CompositeCollection_sizeTest {

    private CompositeCollection<String> c;
    private Collection<String> one;
    private Collection<String> two;

    @BeforeEach
    public void setUp() {
        c = new CompositeCollection<>();
        one = new HashSet<>(Arrays.asList("a", "b", "c"));
        two = new HashSet<>(Arrays.asList("d", "e", "f"));
    }

    @Test
    public void testSizeEmpty() {
        assertEquals(0, c.size());
    }

    @Test
    public void testSizeWithOneCollection() {
        c.addComposited(one);
        assertEquals(one.size(), c.size());
    }

    @Test
    public void testSizeWithMultipleCollections() {
        c.addComposited(one);
        c.addComposited(two);
        assertEquals(one.size() + two.size(), c.size());
    }

    @Test
    public void testSizeAfterRemoveCollection() {
        c.addComposited(one);
        c.addComposited(two);
        c.removeComposited(one);
        assertEquals(two.size(), c.size());
    }

    @Test
    public void testSizeAfterClearCollection() {
        c.addComposited(one);
        c.addComposited(two);
        one.clear();
        assertEquals(two.size(), c.size());
    }
}
