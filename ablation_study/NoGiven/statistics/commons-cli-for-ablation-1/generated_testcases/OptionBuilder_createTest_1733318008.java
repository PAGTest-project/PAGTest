
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OptionBuilder_createTest {

    @Test
    public void testCreateWithLongOpt() {
        // Given
        OptionBuilder.withLongOpt("testLongOpt");

        // When
        Option option = OptionBuilder.create();

        // Then
        assertNotNull(option);
        assertEquals("testLongOpt", option.getLongOpt());
    }

    @Test
    public void testCreateWithoutLongOpt() {
        // Given
        OptionBuilder.withLongOpt(null);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            OptionBuilder.create();
        });
    }
}
