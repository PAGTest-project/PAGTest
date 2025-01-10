
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        assertEquals(flags.hashCode(), clonedFlags.hashCode());
    }

    @Test
    public void testCloneWithAllFlagsOff() {
        flags.turnOffAll();
        Flags clonedFlags = (Flags) flags.clone();
        assertEquals(flags.getFlags(), clonedFlags.getFlags());
        assertEquals(flags.hashCode(), clonedFlags.hashCode());
    }

    @Test
    public void testCloneWithMixedFlags() {
        flags.turnOff(0xAAAAAAAAAAAAAAAAL);
        Flags clonedFlags = (Flags) flags.clone();
        assertEquals(flags.getFlags(), clonedFlags.getFlags());
        assertEquals(flags.hashCode(), clonedFlags.hashCode());
    }

    @Test
    public void testCloneAfterTurnOn() {
        flags.turnOffAll();
        flags.turnOn(0xAAAAAAAAAAAAAAAAL);
        Flags clonedFlags = (Flags) flags.clone();
        assertEquals(flags.getFlags(), clonedFlags.getFlags());
        assertEquals(flags.hashCode(), clonedFlags.hashCode());
    }

    @Test
    public void testCloneAfterTurnOff() {
        flags.turnOff(0xAAAAAAAAAAAAAAAAL);
        Flags clonedFlags = (Flags) flags.clone();
        assertEquals(flags.getFlags(), clonedFlags.getFlags());
        assertEquals(flags.hashCode(), clonedFlags.hashCode());
    }
}
