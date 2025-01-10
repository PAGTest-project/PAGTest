
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    }

    @Test
    public void testCloneWithStateChange() {
        flags.turnOff(0x000000000000000FL); // Turn off some flags
        Flags clonedFlags = (Flags) flags.clone();
        assertEquals(flags.getFlags(), clonedFlags.getFlags());
        assertTrue(flags.equals(clonedFlags));
    }

    @Test
    public void testCloneNotSupported() {
        Flags mockFlags = new Flags(0) {
            @Override
            public Object clone() {
                throw new UnsupportedOperationException("Clone not supported");
            }
        };
        assertThrows(UnsupportedOperationException.class, () -> mockFlags.clone());
    }
}
