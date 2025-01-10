
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
    public void testToStringSingleFlagOn() {
        flags.turnOn(1L);
        assertEquals("0000000000000000000000000000000000000000000000000000000000000001", flags.toString());
    }

    @Test
    public void testToStringMultipleFlagsOn() {
        flags.turnOn(1L);
        flags.turnOn(4L);
        assertEquals("0000000000000000000000000000000000000000000000000000000000001001", flags.toString());
    }

    @Test
    public void testToStringTurnOffFlag() {
        flags.turnOnAll();
        flags.turnOff(1L);
        assertEquals("1111111111111111111111111111111111111111111111111111111111111110", flags.toString());
    }
}
