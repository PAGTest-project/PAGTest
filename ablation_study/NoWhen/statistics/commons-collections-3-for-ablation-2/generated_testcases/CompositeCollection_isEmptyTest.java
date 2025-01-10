
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
    public void testIsEmptyWhenAllCollectionsAreEmpty() {
        c.addComposited(one);
        c.addComposited(two);
        assertTrue(c.isEmpty());
    }

    @Test
    public void testIsEmptyWhenOneCollectionIsNotEmpty() {
        one.add("element");
        c.addComposited(one);
        c.addComposited(two);
        assertFalse(c.isEmpty());
    }

    @Test
    public void testIsEmptyAfterAddingAndRemovingCollections() {
        Collection<String> three = new ArrayList<>();
        three.add("anotherElement");
        c.addComposited(one);
        c.addComposited(two);
        c.addComposited(three);
        assertFalse(c.isEmpty());
        c.removeComposited(three);
        assertTrue(c.isEmpty());
    }
}
