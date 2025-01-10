
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OptionBuilder_createTest {

    @Test
    public void testCreateWithLongOpt() {
        OptionBuilder.withLongOpt("testLongOpt");
        Option option = OptionBuilder.create();
        assertEquals("testLongOpt", option.getLongOpt());
    }

    @Test
    public void testCreateWithoutLongOpt() {
        assertThrows(IllegalArgumentException.class, () -> OptionBuilder.create());
    }
}
