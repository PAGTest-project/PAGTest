
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Flags_cloneTest {

    private Flags flags;

    @BeforeEach
    public void setUp() {
        flags = new Flags(0xFFFFFFFFFFFFFFFFL); // Initialize with all flags on
    }

    @Test
    public void testClone() {
        Flags clonedFlags = (Flags) flags.clone();
        assertEquals(flags.getFlags(), clonedFlags.getFlags());
        assertTrue(flags.equals(clonedFlags));
        assertEquals(flags.toString(), clonedFlags.toString());
    }

    @Test
    public void testCloneWithModifiedState() {
        flags.turnOff(0x00000000FFFFFFFFL); // Turn off lower 32 bits
        Flags clonedFlags = (Flags) flags.clone();
        assertEquals(flags.getFlags(), clonedFlags.getFlags());
        assertTrue(flags.equals(clonedFlags));
        assertEquals(flags.toString(), clonedFlags.toString());
    }
}
