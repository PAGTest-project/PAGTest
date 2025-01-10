
package org.apache.commons.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            OptionBuilder.create();
        });
        assertEquals("must specify longopt", exception.getMessage());
    }
}
