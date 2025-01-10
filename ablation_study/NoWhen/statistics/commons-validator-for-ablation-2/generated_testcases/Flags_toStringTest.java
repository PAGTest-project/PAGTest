
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
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000", flags.toString());
    }

    @Test
    public void testToStringAllFlagsOn() {
        flags.turnOnAll();
        assertEquals("1111111111111111111111111111111111111111111111111111111111111111", flags.toString());
    }

    @Test
    public void testToStringMixedFlags() {
        flags.turnOn(1L); // 2^0
        flags.turnOn(4L); // 2^2
        flags.turnOn(16L); // 2^4
        assertEquals("0000000000000000000000000000000000000000000000000000000000010101", flags.toString());
    }

    @Test
    public void testToStringSingleFlagOn() {
        flags.turnOn(2L); // 2^1
        assertEquals("0000000000000000000000000000000000000000000000000000000000000010", flags.toString());
    }

    @Test
    public void testToStringSingleFlagOff() {
        flags.turnOn(2L); // 2^1
        flags.turnOff(2L); // 2^1
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000", flags.toString());
    }
}
