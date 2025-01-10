
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class OptionBuilder_withLongOptTest {

    @Test
    public void testWithLongOpt() {
        // Given
        String longOpt = "testLongOpt";

        // When
        OptionBuilder.withLongOpt(longOpt);
        Option option = OptionBuilder.create();

        // Then
        assertEquals(longOpt, option.getLongOpt());
    }
}
