
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class OptionBuilder_hasArgsTest {

    @Test
    public void testHasArgs() {
        // Given
        OptionBuilder optionBuilder = OptionBuilder.newInstance();

        // When
        optionBuilder.hasArgs();
        Option option = optionBuilder.create('a');

        // Then
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
    }
}
