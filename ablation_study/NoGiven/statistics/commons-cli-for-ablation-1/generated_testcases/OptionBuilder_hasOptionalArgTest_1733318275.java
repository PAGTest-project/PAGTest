
package org.apache.commons.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OptionBuilder_hasOptionalArgTest {

    @Test
    public void testHasOptionalArg() {
        // Given
        OptionBuilder.reset();

        // When
        OptionBuilder.hasOptionalArg();

        // Then
        Option option = OptionBuilder.create("opt");
        assertTrue(option.hasOptionalArg());
        assertEquals(Option.UNINITIALIZED, option.getArgs());
    }
}
