
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class OptionBuilder_hasOptionalArgsTest {

    @Test
    public void testHasOptionalArgs() {
        // Given
        OptionBuilder optionBuilder = OptionBuilder.getInstance();

        // When
        optionBuilder.hasOptionalArgs();
        Option option = optionBuilder.create("opt");

        // Then
        assertTrue(option.hasOptionalArg());
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
    }
}
