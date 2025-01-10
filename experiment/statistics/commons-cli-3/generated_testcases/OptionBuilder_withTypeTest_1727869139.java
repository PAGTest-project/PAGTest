
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class OptionBuilder_withTypeTest {

    @Test
    public void testWithType() {
        // Given
        OptionBuilder.reset();

        // When
        OptionBuilder.withType(Integer.class);
        Option option = OptionBuilder.create('i');

        // Then
        assertEquals(Integer.class, option.getType());
    }
}
