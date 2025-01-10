
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedOrderComparator_addAsEqualTest {

    private FixedOrderComparator<String> comparator;

    @BeforeEach
    void setUp() {
        comparator = new FixedOrderComparator<>();
    }

    @Test
    void testAddAsEqual_NewObjectAdded() {
        // Given
        comparator.add("existingObj");

        // When
        boolean result = comparator.addAsEqual("existingObj", "newObj");

        // Then
        assertTrue(result);
    }

    @Test
    void testAddAsEqual_ExistingObjectNotKnown() {
        // Given
        // No existing object added

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> comparator.addAsEqual("existingObj", "newObj"));
    }

    @Test
    void testAddAsEqual_ObjectAlreadyKnown() {
        // Given
        comparator.add("existingObj");
        comparator.addAsEqual("existingObj", "newObj");

        // When
        boolean result = comparator.addAsEqual("existingObj", "newObj");

        // Then
        assertFalse(result);
    }
}
