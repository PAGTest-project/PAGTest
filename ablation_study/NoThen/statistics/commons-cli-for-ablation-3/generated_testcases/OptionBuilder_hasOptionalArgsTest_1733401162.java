
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class OptionBuilder_hasOptionalArgsTest {

    @Test
    public void testHasOptionalArgs() {
        // Given
        OptionBuilder.reset();

        // When
        OptionBuilder.hasOptionalArgs();

        // Then
        assertEquals(Option.UNLIMITED_VALUES, OptionBuilder.argCount);
        assertTrue(OptionBuilder.optionalArg);
    }

    @Test
    public void testHasOptionalArgsWithCreate() {
        // Given
        OptionBuilder.reset();
        OptionBuilder.withLongOpt("testOption").withDescription("Test option description");

        // When
        OptionBuilder.hasOptionalArgs();
        Option option = OptionBuilder.create("t");

        // Then
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
        assertTrue(option.hasOptionalArg());
    }
}
