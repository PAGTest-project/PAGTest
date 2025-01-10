
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
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
        one = new ArrayList<>();
        two = new ArrayList<>();
    }

    @Test
    public void testIsEmptyWhenEmpty() {
        assertTrue(c.isEmpty());
    }

    @Test
    public void testIsEmptyWhenNotEmpty() {
        one.add("1");
        c.addComposited(one);
        assertFalse(c.isEmpty());
    }

    @Test
    public void testIsEmptyAfterClear() {
        one.add("1");
        c.addComposited(one);
        c.clear();
        assertTrue(c.isEmpty());
    }

    @Test
    public void testIsEmptyAfterAddAndRemove() {
        one.add("1");
        c.addComposited(one);
        c.remove("1");
        assertTrue(c.isEmpty());
    }
}
