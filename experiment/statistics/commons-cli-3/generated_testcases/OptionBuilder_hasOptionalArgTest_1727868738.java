
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OptionBuilder_hasOptionalArgTest {

    @Test
    public void testHasOptionalArg() {
        // Given
        OptionBuilder optionBuilder = OptionBuilder.newInstance();

        // When
        optionBuilder.hasOptionalArg();
        Option option = optionBuilder.create("opt");

        // Then
        assertTrue(option.hasOptionalArg());
        assertEquals(1, option.getArgs());
    }
}
