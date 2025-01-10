
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class OptionBuilder_hasOptionalArgTest {

    @Test
    public void testHasOptionalArg() {
        // When
        OptionBuilder.withLongOpt("testOption");
        OptionBuilder.hasOptionalArg();
        Option option = OptionBuilder.create("t");

        // Then
        assertEquals("testOption", option.getLongOpt());
        assertEquals(1, option.getArgs());
        assertTrue(option.hasOptionalArg());
    }

    @Test
    public void testHasOptionalArgWithoutLongOpt() {
        // When
        OptionBuilder.hasOptionalArg();

        try {
            OptionBuilder.create();
        } catch (IllegalArgumentException e) {
            // expected
            assertEquals("must specify longopt", e.getMessage());
        }
    }
}
