
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class OptionBuilder_withDescriptionTest {

    @Test
    public void testWithDescription() {
        // Given
        String description = "Test Description";

        // When
        OptionBuilder.withDescription(description);
        Option option = OptionBuilder.create('t');

        // Then
        assertEquals(description, option.getDescription());
    }
}
