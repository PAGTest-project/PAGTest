
package org.apache.commons.collections4.bag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class TreeBag_addTest {

    private TreeBag<ComparableObject> treeBag;

    @BeforeEach
    void setUp() {
        treeBag = new TreeBag<>();
    }

    @Test
    void testAddWithComparableObject() {
        // Given
        ComparableObject comparableObject = new ComparableObject();

        // When
        boolean result = treeBag.add(comparableObject);

        // Then
        assertTrue(result);
    }

    @Test
    void testAddWithNonComparableObjectAndNoComparator() {
        // Given
        TreeBag<NonComparableObject> treeBag = new TreeBag<>();
        NonComparableObject nonComparableObject = new NonComparableObject();

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            treeBag.add(nonComparableObject);
        });
        assertEquals("Objects of type " + nonComparableObject.getClass() + " cannot be added to " +
                     "a naturally ordered TreeBag as it does not implement Comparable", exception.getMessage());
    }

    @Test
    void testAddWithNonComparableObjectAndComparator() {
        // Given
        TreeBag<NonComparableObject> treeBag = new TreeBag<>(Comparator.naturalOrder());
        NonComparableObject nonComparableObject = new NonComparableObject();

        // When
        boolean result = treeBag.add(nonComparableObject);

        // Then
        assertTrue(result);
    }

    private static class ComparableObject implements Comparable<ComparableObject> {
        @Override
        public int compareTo(ComparableObject o) {
            return 0;
        }
    }

    private static class NonComparableObject {
    }
}
