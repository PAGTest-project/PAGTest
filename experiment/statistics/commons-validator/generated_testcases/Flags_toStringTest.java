
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Flags_toStringTest {

    @Test
    public void testToStringWithAllFlagsOff() {
        Flags flags = new Flags(0);
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000", flags.toString());
    }

    @Test
    public void testToStringWithAllFlagsOn() {
        Flags flags = new Flags(0xFFFFFFFFFFFFFFFFL);
        assertEquals("1111111111111111111111111111111111111111111111111111111111111111", flags.toString());
    }

    @Test
    public void testToStringWithMixedFlags() {
        Flags flags = new Flags(45); // Binary: 101101
        assertEquals("0000000000000000000000000000000000000000000000000000000000101101", flags.toString());
    }
}
