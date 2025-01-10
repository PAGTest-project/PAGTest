
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class OptionBuilder_withTypeTest {

    @Test
    public void testWithType() {
        // Given
        OptionBuilder optionBuilder = OptionBuilder.getInstance();

        // When
        optionBuilder.withType(Integer.class);
        Option option = optionBuilder.create('i');

        // Then
        assertEquals(Integer.class, option.getType());
    }
}
