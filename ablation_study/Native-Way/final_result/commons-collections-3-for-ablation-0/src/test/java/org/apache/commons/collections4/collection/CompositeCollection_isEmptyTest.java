
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_isEmptyTest {
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
    public void testIsEmpty_AllCollectionsEmpty() {
        c.addComposited(one, two);
        assertTrue(c.isEmpty());
    }

    @Test
    public void testIsEmpty_OneCollectionNotEmpty() {
        one.add("1");
        c.addComposited(one, two);
        assertFalse(c.isEmpty());
    }

    @Test
    public void testIsEmpty_AllCollectionsNotEmpty() {
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        assertFalse(c.isEmpty());
    }

    @Test
    public void testIsEmpty_AfterClear() {
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        c.clear();
        assertTrue(c.isEmpty());
    }
}
