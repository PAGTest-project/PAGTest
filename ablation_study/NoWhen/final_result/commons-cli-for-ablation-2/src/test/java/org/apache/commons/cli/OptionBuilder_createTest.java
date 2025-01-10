
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OptionBuilder_createTest {

    @Test
    public void testCreate_withLongOpt() {
        // Given
        OptionBuilder.withLongOpt("testLongOpt");

        // When
        Option option = OptionBuilder.create();

        // Then
        assertNotNull(option);
        assertEquals("testLongOpt", option.getLongOpt());
    }

    @Test
    public void testCreate_withoutLongOpt() {
        // Given
        OptionBuilder.withLongOpt(null);

        // When & Then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            OptionBuilder.create();
        });
        assertEquals("must specify longopt", exception.getMessage());
    }
}
