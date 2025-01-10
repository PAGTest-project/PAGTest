
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OptionBuilder_hasOptionalArgsTest {

    @Test
    public void testHasOptionalArgs() {
        // When
        OptionBuilder.hasOptionalArgs();

        // Then
        Option option = OptionBuilder.withLongOpt("testOption").create();
        assertTrue(option.hasOptionalArg());
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
    }

    @Test
    public void testHasOptionalArgsWithReset() {
        // When
        OptionBuilder.hasOptionalArgs();
        OptionBuilder.reset();

        // Then
        Option option = OptionBuilder.withLongOpt("testOption").create();
        assertFalse(option.hasOptionalArg());
        assertEquals(Option.UNINITIALIZED, option.getArgs());
    }

    @Test
    public void testHasOptionalArgsWithLongOpt() {
        // When
        OptionBuilder.withLongOpt("testOption").hasOptionalArgs();

        // Then
        Option option = OptionBuilder.create();
        assertTrue(option.hasOptionalArg());
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
        assertEquals("testOption", option.getLongOpt());
    }

    @Test
    public void testHasOptionalArgsWithArg() {
        // When
        OptionBuilder.hasOptionalArgs();

        // Then
        Option option = OptionBuilder.withLongOpt("testOption").create();
        assertTrue(option.hasOptionalArg());
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());

        // When
        OptionBuilder.reset();
        OptionBuilder.hasArg();

        // Then
        option = OptionBuilder.withLongOpt("testOption").create();
        assertFalse(option.hasOptionalArg());
        assertEquals(1, option.getArgs());
    }
}
