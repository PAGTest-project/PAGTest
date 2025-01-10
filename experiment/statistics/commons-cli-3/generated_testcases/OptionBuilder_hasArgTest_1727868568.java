
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class OptionBuilder_hasArgTest {

    @Test
    public void testHasArgSetsArgCountToOne() {
        // Given
        OptionBuilder.reset();

        // When
        OptionBuilder.hasArg();

        // Then
        assertEquals(1, OptionBuilder.argCount);
    }
}
