
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class OptionBuilder_withValueSeparatorTest {

    @Test
    public void testWithValueSeparator() {
        // Given
        OptionBuilder.reset();

        // When
        OptionBuilder.withValueSeparator();
        Option option = OptionBuilder.create('D');

        // Then
        assertEquals(Char.EQUAL, option.getValueSeparator());
    }
}
