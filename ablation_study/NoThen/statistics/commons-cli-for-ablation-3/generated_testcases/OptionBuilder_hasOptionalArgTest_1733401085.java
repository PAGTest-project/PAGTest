
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OptionBuilder_hasOptionalArgTest {

    @Test
    public void testHasOptionalArg() {
        // Given
        OptionBuilder.reset();

        // When
        OptionBuilder.hasOptionalArg();
        Option option = OptionBuilder.create("opt");

        // Then
        assertTrue(option.hasOptionalArg());
        assertEquals(1, option.getArgs());
    }

    @Test
    public void testHasOptionalArgWithArgName() {
        // Given
        OptionBuilder.reset();

        // When
        OptionBuilder.hasOptionalArg().withArgName("value");
        Option option = OptionBuilder.create("opt");

        // Then
        assertTrue(option.hasOptionalArg());
        assertEquals(1, option.getArgs());
        assertEquals("value", option.getArgName());
    }

    @Test
    public void testHasOptionalArgWithDescription() {
        // Given
        OptionBuilder.reset();

        // When
        OptionBuilder.hasOptionalArg().withDescription("optional arg option");
        Option option = OptionBuilder.create("opt");

        // Then
        assertTrue(option.hasOptionalArg());
        assertEquals(1, option.getArgs());
        assertEquals("optional arg option", option.getDescription());
    }

    @Test
    public void testHasOptionalArgWithLongOpt() {
        // Given
        OptionBuilder.reset();

        // When
        OptionBuilder.hasOptionalArg().withLongOpt("longOpt");
        Option option = OptionBuilder.create("opt");

        // Then
        assertTrue(option.hasOptionalArg());
        assertEquals(1, option.getArgs());
        assertEquals("longOpt", option.getLongOpt());
    }

    @Test
    public void testHasOptionalArgWithRequired() {
        // Given
        OptionBuilder.reset();

        // When
        OptionBuilder.hasOptionalArg().isRequired();
        Option option = OptionBuilder.create("opt");

        // Then
        assertTrue(option.hasOptionalArg());
        assertEquals(1, option.getArgs());
        assertTrue(option.isRequired());
    }
}
