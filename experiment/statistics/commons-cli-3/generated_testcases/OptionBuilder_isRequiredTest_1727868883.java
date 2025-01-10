
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class OptionBuilder_isRequiredTest {

    @Test
    public void testIsRequired() {
        // Given
        OptionBuilder.reset();

        // When
        OptionBuilder.isRequired();
        Option option = OptionBuilder.create("opt");

        // Then
        assertTrue(option.isRequired());
    }
}
