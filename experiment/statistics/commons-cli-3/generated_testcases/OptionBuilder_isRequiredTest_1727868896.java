
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class OptionBuilder_isRequiredTest {

    @Test
    public void testIsRequired() {
        // Given
        OptionBuilder optionBuilder = OptionBuilder.newInstance();

        // When
        optionBuilder.isRequired();
        Option option = optionBuilder.create("opt");

        // Then
        assertTrue(option.isRequired());
    }
}
