
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeSet_removeAllTest {

    private CompositeSet<String> compositeSet;
    private Set<String> set1;
    private Set<String> set2;

    @BeforeEach
    public void setUp() {
        set1 = new HashSet<>(Arrays.asList("A", "B", "C"));
        set2 = new HashSet<>(Arrays.asList("D", "E", "F"));
        compositeSet = new CompositeSet<>(set1, set2);
    }

    @Test
    public void testRemoveAllWithNonEmptyCollection() {
        Collection<String> toRemove = Arrays.asList("A", "D");
        assertTrue(compositeSet.removeAll(toRemove));
        assertFalse(set1.contains("A"));
        assertFalse(set2.contains("D"));
    }

    @Test
    public void testRemoveAllWithEmptyCollection() {
        Collection<String> toRemove = Arrays.asList();
        assertFalse(compositeSet.removeAll(toRemove));
    }

    @Test
    public void testRemoveAllWithNullCollection() {
        assertFalse(compositeSet.removeAll(null));
    }

    @Test
    public void testRemoveAllWithNonExistentElements() {
        Collection<String> toRemove = Arrays.asList("X", "Y");
        assertFalse(compositeSet.removeAll(toRemove));
    }

    @Test
    public void testRemoveAllWithAllElements() {
        Collection<String> toRemove = Arrays.asList("A", "B", "C", "D", "E", "F");
        assertTrue(compositeSet.removeAll(toRemove));
        assertTrue(set1.isEmpty());
        assertTrue(set2.isEmpty());
    }
}
