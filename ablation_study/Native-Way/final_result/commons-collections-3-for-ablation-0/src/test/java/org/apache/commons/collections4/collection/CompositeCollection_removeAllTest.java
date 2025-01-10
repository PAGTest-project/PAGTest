
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_removeAllTest {
    private CompositeCollection<String> c;
    private Collection<String> one;
    private Collection<String> two;

    @BeforeEach
    public void setUp() {
        c = new CompositeCollection<>();
        one = new HashSet<>(Arrays.asList("1", "2", "3"));
        two = new HashSet<>(Arrays.asList("4", "5", "6"));
        c.addComposited(one, two);
    }

    @Test
    public void testRemoveAllWithNonEmptyCollection() {
        Collection<String> toRemove = new ArrayList<>(Arrays.asList("1", "4"));
        assertTrue(c.removeAll(toRemove));
        assertFalse(one.contains("1"));
        assertFalse(two.contains("4"));
    }

    @Test
    public void testRemoveAllWithEmptyCollection() {
        Collection<String> toRemove = new ArrayList<>();
        assertFalse(c.removeAll(toRemove));
    }

    @Test
    public void testRemoveAllWithNullCollection() {
        assertFalse(c.removeAll(null));
    }

    @Test
    public void testRemoveAllWithAllElements() {
        Collection<String> toRemove = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        assertTrue(c.removeAll(toRemove));
        assertTrue(one.isEmpty());
        assertTrue(two.isEmpty());
    }

    @Test
    public void testRemoveAllWithNonExistentElements() {
        Collection<String> toRemove = new ArrayList<>(Arrays.asList("7", "8"));
        assertFalse(c.removeAll(toRemove));
    }
}
