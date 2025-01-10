
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_containsAllTest {
    private CompositeCollection<String> c;
    private Collection<String> one;
    private Collection<String> two;

    @BeforeEach
    protected void setUpTest() {
        c = new CompositeCollection<>();
        one = new HashSet<>(Arrays.asList("a", "b", "c"));
        two = new HashSet<>(Arrays.asList("d", "e", "f"));
        c.addComposited(one, two);
    }

    @Test
    public void testContainsAllWithNullCollection() {
        assertFalse(c.containsAll(null));
    }

    @Test
    public void testContainsAllWithEmptyCollection() {
        assertTrue(c.containsAll(new ArrayList<>()));
    }

    @Test
    public void testContainsAllWithAllElementsPresent() {
        Collection<String> testCollection = Arrays.asList("a", "d");
        assertTrue(c.containsAll(testCollection));
    }

    @Test
    public void testContainsAllWithSomeElementsMissing() {
        Collection<String> testCollection = Arrays.asList("a", "x");
        assertFalse(c.containsAll(testCollection));
    }

    @Test
    public void testContainsAllAfterAddingElements() {
        Collection<String> newCollection = new HashSet<>(Arrays.asList("g"));
        c.addComposited(newCollection);
        Collection<String> testCollection = Arrays.asList("a", "g");
        assertTrue(c.containsAll(testCollection));
    }

    @Test
    public void testContainsAllAfterClearingCollection() {
        c.clear();
        Collection<String> testCollection = Arrays.asList("a", "d");
        assertFalse(c.containsAll(testCollection));
    }
}
