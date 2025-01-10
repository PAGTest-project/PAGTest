
package org.apache.commons.collections4.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FixedOrderComparator_addAsEqualTest {

    private FixedOrderComparator<String> comparator;

    @BeforeEach
    void setUp() {
        comparator = new FixedOrderComparator<>(Arrays.asList("existingObj"));
    }

    @Test
    void testAddAsEqual_NewObjectAdded() {
        // Given
        String existingObj = "existingObj";
        String newObj = "newObj";

        // When
        boolean result = comparator.addAsEqual(existingObj, newObj);

        // Then
        assertTrue(result);
    }

    @Test
    void testAddAsEqual_ExistingObjectNotKnown() {
        // Given
        String existingObj = "unknownObj";
        String newObj = "newObj";

        // When / Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            comparator.addAsEqual(existingObj, newObj);
        });
        assertEquals("unknownObj not known to " + comparator, exception.getMessage());
    }
}
