
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Flags_cloneTest {

    private Flags flags;

    @BeforeEach
    public void setUp() {
        flags = new Flags(98432);
    }

    @Test
    public void testClone() {
        Flags clonedFlags = (Flags) flags.clone();
        assertEquals(flags.getFlags(), clonedFlags.getFlags());
    }

    @Test
    public void testCloneNotSupported() {
        flags = new Flags(98432) {
            @Override
            public Object clone() throws CloneNotSupportedException {
                throw new CloneNotSupportedException();
            }
        };
        assertThrows(UnsupportedOperationException.class, () -> flags.clone());
    }
}
