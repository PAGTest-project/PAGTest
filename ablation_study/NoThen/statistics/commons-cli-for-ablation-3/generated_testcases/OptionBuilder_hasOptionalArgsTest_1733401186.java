
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class OptionBuilder_hasOptionalArgsTest {

    @Test
    public void testHasOptionalArgs() {
        // Given
        OptionBuilder optionBuilder = new OptionBuilder();

        // When
        optionBuilder.hasOptionalArgs();

        // Then
        assertEquals(Option.UNLIMITED_VALUES, optionBuilder.argCount);
        assertTrue(optionBuilder.optionalArg);
    }

    @Test
    public void testHasOptionalArgsWithCreate() {
        // Given
        OptionBuilder optionBuilder = new OptionBuilder();
        optionBuilder.withLongOpt("testOption").withDescription("Test option description");

        // When
        optionBuilder.hasOptionalArgs();
        Option option = optionBuilder.create("t");

        // Then
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
        assertTrue(option.hasOptionalArg());
    }
}
