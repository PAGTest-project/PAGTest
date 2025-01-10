
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class OptionBuilder_hasArgTest {

    @Test
    public void testHasArgSetsArgCountToOne() {
        // Given
        OptionBuilder optionBuilder = new OptionBuilder();

        // When
        optionBuilder.hasArg();

        // Then
        assertEquals(1, optionBuilder.argCount);
    }
}
