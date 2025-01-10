
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class OptionBuilder_hasArgsTest {

    @Test
    public void testHasArgs() {
        // Given
        OptionBuilder.reset();

        // When
        OptionBuilder.hasArgs();
        Option option = OptionBuilder.create('a');

        // Then
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
    }
}
