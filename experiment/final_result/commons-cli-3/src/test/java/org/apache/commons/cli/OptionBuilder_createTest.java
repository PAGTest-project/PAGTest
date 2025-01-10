
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class OptionBuilder_createTest {

    @Test
    public void testCreateWithLongOpt() {
        OptionBuilder.withLongOpt("testLongOpt").withDescription("testDescription");
        Option option = OptionBuilder.create();
        assertEquals("testLongOpt", option.getLongOpt());
        assertEquals("testDescription", option.getDescription());
    }

    @Test
    public void testCreateWithoutLongOpt() {
        assertThrows(IllegalArgumentException.class, () -> {
            OptionBuilder.create();
        });
    }
}
