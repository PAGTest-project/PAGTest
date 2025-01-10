
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class OptionBuilder_withArgNameTest {

    @Test
    public void testWithArgName() {
        // Given
        String argName = "argumentName";

        // When
        OptionBuilder.withArgName(argName);
        Option option = OptionBuilder.create("opt");

        // Then
        assertEquals(argName, option.getArgName());
    }
}
