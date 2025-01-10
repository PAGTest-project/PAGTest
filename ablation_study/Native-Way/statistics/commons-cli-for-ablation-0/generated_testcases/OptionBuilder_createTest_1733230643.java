
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
        try {
            OptionBuilder.create();
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("must specify longopt", e.getMessage());
        }
    }

    @Test
    public void testCreateWithAllProperties() {
        OptionBuilder.withLongOpt("testLongOpt")
                     .hasArg()
                     .isRequired()
                     .hasArgs()
                     .withType(Float.class)
                     .withDescription("this is a test option")
                     .withValueSeparator('=');

        Option option = OptionBuilder.create();

        assertEquals("testLongOpt", option.getLongOpt());
        assertEquals("this is a test option", option.getDescription());
        assertEquals(Float.class, option.getType());
        assertTrue(option.hasArg());
        assertTrue(option.isRequired());
        assertTrue(option.hasArgs());
        assertEquals('=', option.getValueSeparator());
    }

    @Test
    public void testCreateWithReset() {
        OptionBuilder.withLongOpt("testLongOpt");
        OptionBuilder.reset();
        try {
            OptionBuilder.create();
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("must specify longopt", e.getMessage());
        }
    }
}
