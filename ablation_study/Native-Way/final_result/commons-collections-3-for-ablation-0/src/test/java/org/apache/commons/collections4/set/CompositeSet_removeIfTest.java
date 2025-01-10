
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeSet_removeIfTest {

    private CompositeSet<String> compositeSet;
    private Set<String> set1;
    private Set<String> set2;

    @BeforeEach
    public void setUp() {
        set1 = new HashSet<>();
        set1.add("A");
        set1.add("B");

        set2 = new HashSet<>();
        set2.add("C");
        set2.add("D");

        compositeSet = new CompositeSet<>(set1, set2);
    }

    @Test
    public void testRemoveIf_NullFilter() {
        assertFalse(compositeSet.removeIf(null));
    }

    @Test
    public void testRemoveIf_NoMatches() {
        Predicate<String> filter = s -> s.equals("E");
        assertFalse(compositeSet.removeIf(filter));
    }

    @Test
    public void testRemoveIf_MatchesInOneSet() {
        Predicate<String> filter = s -> s.equals("A");
        assertTrue(compositeSet.removeIf(filter));
        assertFalse(set1.contains("A"));
        assertTrue(set1.contains("B"));
        assertTrue(set2.contains("C"));
        assertTrue(set2.contains("D"));
    }

    @Test
    public void testRemoveIf_MatchesInMultipleSets() {
        Predicate<String> filter = s -> s.equals("B") || s.equals("C");
        assertTrue(compositeSet.removeIf(filter));
        assertFalse(set1.contains("B"));
        assertTrue(set1.contains("A"));
        assertFalse(set2.contains("C"));
        assertTrue(set2.contains("D"));
    }

    @Test
    public void testRemoveIf_MatchesInAllSets() {
        Predicate<String> filter = s -> s.equals("A") || s.equals("C");
        assertTrue(compositeSet.removeIf(filter));
        assertFalse(set1.contains("A"));
        assertTrue(set1.contains("B"));
        assertFalse(set2.contains("C"));
        assertTrue(set2.contains("D"));
    }
}
