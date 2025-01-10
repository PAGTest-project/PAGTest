
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OptionBuilder_hasOptionalArgsTest {

    @Test
    public void testHasOptionalArgs() {
        // When
        OptionBuilder.reset();
        OptionBuilder.withLongOpt("testOption");
        OptionBuilder.hasOptionalArgs();
        Option option = OptionBuilder.create();

        // Then
        assertTrue(option.hasOptionalArg());
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
    }

    @Test
    public void testHasOptionalArgsWithReset() {
        // When
        OptionBuilder.reset();
        OptionBuilder.withLongOpt("testOption");
        OptionBuilder.hasOptionalArgs();
        Option option = OptionBuilder.create();

        // Then
        assertTrue(option.hasOptionalArg());
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());

        // Ensure reset works correctly
        OptionBuilder.reset();
        OptionBuilder.withLongOpt("anotherOption");
        OptionBuilder.hasArg();
        Option anotherOption = OptionBuilder.create();

        assertFalse(anotherOption.hasOptionalArg());
        assertEquals(1, anotherOption.getArgs());
    }
}
