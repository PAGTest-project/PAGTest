
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Flags_cloneTest {
    private Flags flags;

    @BeforeEach
    public void setUp() {
        flags = new Flags(0xFFFFFFFFFFFFFFFFL);
    }

    @Test
    public void testCloneWithAllFlagsOn() {
        Flags clonedFlags = (Flags) flags.clone();
        assertEquals(flags.getFlags(), clonedFlags.getFlags());
        assertTrue(flags.equals(clonedFlags));
    }

    @Test
    public void testCloneWithAllFlagsOff() {
        flags.turnOffAll();
        Flags clonedFlags = (Flags) flags.clone();
        assertEquals(flags.getFlags(), clonedFlags.getFlags());
        assertTrue(flags.equals(clonedFlags));
    }

    @Test
    public void testCloneWithMixedFlags() {
        flags.turnOff(0xAAAAAAAAAAAAAAAAL); // Turn off every other flag
        Flags clonedFlags = (Flags) flags.clone();
        assertEquals(flags.getFlags(), clonedFlags.getFlags());
        assertTrue(flags.equals(clonedFlags));
    }
}
