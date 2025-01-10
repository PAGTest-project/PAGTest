
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OptionBuilder_hasOptionalArgTest {

    @Test
    public void testHasOptionalArg() {
        // Given
        OptionBuilder.hasOptionalArg();

        // When
        Option option = OptionBuilder.create("opt");

        // Then
        assertTrue(option.hasOptionalArg());
        assertEquals(1, option.getArgs());
    }

    @Test
    public void testHasOptionalArgWithReset() {
        // Given
        OptionBuilder.hasOptionalArg();

        // When
        Option option = OptionBuilder.create("opt");

        // Then
        assertTrue(option.hasOptionalArg());
        assertEquals(1, option.getArgs());

        // Reset and verify
        OptionBuilder.hasOptionalArg(); // Reset by setting optional arg again
        Option newOption = OptionBuilder.create("newOpt");
        assertTrue(newOption.hasOptionalArg());
        assertEquals(1, newOption.getArgs());
    }
}
