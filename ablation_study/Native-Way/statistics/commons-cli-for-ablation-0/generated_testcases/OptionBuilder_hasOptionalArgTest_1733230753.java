
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OptionBuilder_hasOptionalArgTest {

    @Test
    public void testHasOptionalArg() {
        OptionBuilder.hasOptionalArg();
        Option option = OptionBuilder.create("o");

        assertEquals("o", option.getOpt());
        assertTrue(option.hasOptionalArg());
        assertEquals(1, option.getArgs());
    }

    @Test
    public void testHasOptionalArgWithReset() {
        OptionBuilder.hasOptionalArg();
        Option option = OptionBuilder.create("o");

        assertEquals("o", option.getOpt());
        assertTrue(option.hasOptionalArg());
        assertEquals(1, option.getArgs());

        // Ensure reset works correctly
        OptionBuilder.reset();
        Option newOption = OptionBuilder.create("n");
        assertFalse(newOption.hasOptionalArg());
        assertEquals(Option.UNINITIALIZED, newOption.getArgs());
    }
}
