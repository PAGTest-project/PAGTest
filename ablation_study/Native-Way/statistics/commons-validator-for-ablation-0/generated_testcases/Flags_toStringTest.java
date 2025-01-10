
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Flags_toStringTest {
    private Flags flags;

    @BeforeEach
    public void setUp() {
        flags = new Flags();
    }

    @Test
    public void testToStringAllFlagsOff() {
        flags.turnOffAll();
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000", flags.toString());
    }

    @Test
    public void testToStringAllFlagsOn() {
        flags.turnOnAll();
        assertEquals("1111111111111111111111111111111111111111111111111111111111111111", flags.toString());
    }

    @Test
    public void testToStringMixedFlags() {
        flags.turnOn(0x1L); // 0001
        flags.turnOn(0x4L); // 0100
        assertEquals("0000000000000000000000000000000000000000000000000000000000000101", flags.toString());
    }

    @Test
    public void testToStringSingleFlagOn() {
        flags.turnOn(0x8000000000000000L); // 1000000000000000000000000000000000000000000000000000000000000000
        assertEquals("1000000000000000000000000000000000000000000000000000000000000000", flags.toString());
    }
}
