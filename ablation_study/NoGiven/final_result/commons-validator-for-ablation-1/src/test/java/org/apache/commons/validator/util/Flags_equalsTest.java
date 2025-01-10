
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Flags_equalsTest {

    private Flags flags1;
    private Flags flags2;

    @BeforeEach
    public void setUp() {
        flags1 = new Flags(0xFFFFFFFFFFFFFFFFL);
        flags2 = new Flags(0xFFFFFFFFFFFFFFFFL);
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(flags1.equals(flags1));
    }

    @Test
    public void testEqualsDifferentObjectType() {
        assertFalse(flags1.equals("Not a Flags object"));
    }

    @Test
    public void testEqualsDifferentFlags() {
        flags2.turnOff(0x8000000000000000L);
        assertFalse(flags1.equals(flags2));
    }

    @Test
    public void testEqualsSameFlags() {
        assertTrue(flags1.equals(flags2));
    }

    @Test
    public void testEqualsAfterTurnOn() {
        flags2.turnOff(0x8000000000000000L);
        flags2.turnOn(0x8000000000000000L);
        assertTrue(flags1.equals(flags2));
    }

    @Test
    public void testEqualsAfterTurnOff() {
        flags2.turnOff(0x8000000000000000L);
        assertFalse(flags1.equals(flags2));
    }

    @Test
    public void testEqualsHashCodeConsistency() {
        if (flags1.equals(flags2)) {
            assertEquals(flags1.hashCode(), flags2.hashCode());
        }
    }

    @Test
    public void testEqualsStateVerification() {
        final long highOrderBit = 0x8000000000000000L;
        assertTrue(flags1.isOn(highOrderBit));
        assertTrue(flags2.isOn(highOrderBit));
        assertTrue(flags1.equals(flags2));
    }
}
