
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_clearTest {
    private CompositeCollection<String> c;
    private Collection<String> one;
    private Collection<String> two;

    @BeforeEach
    public void setUp() {
        c = new CompositeCollection<>();
        one = new HashSet<>();
        two = new HashSet<>();
        one.add("element1");
        one.add("element2");
        two.add("element3");
        c.addComposited(one, two);
    }

    @Test
    public void testClear() {
        // Given
        assertEquals(3, c.size());
        assertFalse(c.isEmpty());

        // When
        c.clear();

        // Then
        assertEquals(0, c.size());
        assertTrue(c.isEmpty());
    }

    @Test
    public void testClearWithEmptyComposite() {
        // Given
        CompositeCollection<String> emptyComposite = new CompositeCollection<>();

        // When
        emptyComposite.clear();

        // Then
        assertEquals(0, emptyComposite.size());
        assertTrue(emptyComposite.isEmpty());
    }

    @Test
    public void testClearWithSingleCollection() {
        // Given
        CompositeCollection<String> singleComposite = new CompositeCollection<>(one);
        assertEquals(2, singleComposite.size());
        assertFalse(singleComposite.isEmpty());

        // When
        singleComposite.clear();

        // Then
        assertEquals(0, singleComposite.size());
        assertTrue(singleComposite.isEmpty());
    }

    @Test
    public void testClearWithMultipleCollections() {
        // Given
        Collection<String> three = new HashSet<>();
        three.add("element4");
        c.addComposited(three);
        assertEquals(4, c.size());
        assertFalse(c.isEmpty());

        // When
        c.clear();

        // Then
        assertEquals(0, c.size());
        assertTrue(c.isEmpty());
    }
}
