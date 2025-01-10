
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
    public void testEqualsDifferentClass() {
        assertFalse(flags1.equals("Not a Flags object"));
    }

    @Test
    public void testEqualsSameFlags() {
        assertTrue(flags1.equals(flags2));
    }

    @Test
    public void testEqualsDifferentFlags() {
        flags2.turnOff(0x0000000000000001L);
        assertFalse(flags1.equals(flags2));
    }

    @Test
    public void testEqualsAfterStateChange() {
        flags1.turnOff(0x0000000000000001L);
        flags2.turnOff(0x0000000000000001L);
        assertTrue(flags1.equals(flags2));
    }
}
