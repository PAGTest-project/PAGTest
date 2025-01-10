
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Options_addRequiredOptionTest {
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testAddRequiredOption() {
        options.addRequiredOption("r", "required", true, "required option with argument");

        assertTrue(options.hasOption("r"));
        assertTrue(options.hasLongOption("required"));
        assertTrue(options.getOption("r").isRequired());
        assertEquals("required option with argument", options.getOption("r").getDescription());
    }

    @Test
    public void testAddRequiredOptionWithoutArgument() {
        options.addRequiredOption("n", "noarg", false, "required option without argument");

        assertTrue(options.hasOption("n"));
        assertTrue(options.hasLongOption("noarg"));
        assertTrue(options.getOption("n").isRequired());
        assertFalse(options.getOption("n").hasArg());
        assertEquals("required option without argument", options.getOption("n").getDescription());
    }

    @Test
    public void testAddRequiredOptionWithLongOptOnly() {
        options.addRequiredOption(null, "longonly", true, "required option with long opt only");

        assertFalse(options.hasOption(null));
        assertTrue(options.hasLongOption("longonly"));
        assertTrue(options.getOption("longonly").isRequired());
        assertEquals("required option with long opt only", options.getOption("longonly").getDescription());
    }
}
