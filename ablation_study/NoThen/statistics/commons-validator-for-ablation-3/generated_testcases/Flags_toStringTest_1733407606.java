
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
        assertEquals("00000000000000000000000000000000", flags.toString());
    }

    @Test
    public void testToStringAllFlagsOn() {
        flags.turnOnAll();
        assertEquals("11111111111111111111111111111111", flags.toString());
    }

    @Test
    public void testToStringMixedFlags() {
        flags.turnOn(1); // 0001
        flags.turnOn(4); // 0100
        assertEquals("00000000000000000000000000001001", flags.toString());
    }
}
