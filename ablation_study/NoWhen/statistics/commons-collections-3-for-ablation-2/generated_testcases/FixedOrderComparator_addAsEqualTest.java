
package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FixedOrderComparator_addAsEqualTest {

    private FixedOrderComparator<String> comparator;

    @BeforeEach
    public void setUp() {
        comparator = new FixedOrderComparator<>();
    }

    @Test
    public void testAddAsEqual_Success() {
        comparator.add("existingObj");
        assertTrue(comparator.addAsEqual("existingObj", "newObj"));
        assertEquals(0, comparator.compare("existingObj", "newObj"));
    }

    @Test
    public void testAddAsEqual_ExistingObjNotKnown() {
        assertThrows(IllegalArgumentException.class, () -> {
            comparator.addAsEqual("unknownObj", "newObj");
        });
    }

    @Test
    public void testAddAsEqual_AlreadyKnown() {
        comparator.add("existingObj");
        comparator.addAsEqual("existingObj", "newObj");
        assertFalse(comparator.addAsEqual("existingObj", "newObj"));
    }

    @Test
    public void testAddAsEqual_LockedComparator() {
        comparator.add("existingObj");
        comparator.compare("existingObj", "existingObj"); // Lock the comparator
        assertThrows(UnsupportedOperationException.class, () -> {
            comparator.addAsEqual("existingObj", "newObj");
        });
    }
}
