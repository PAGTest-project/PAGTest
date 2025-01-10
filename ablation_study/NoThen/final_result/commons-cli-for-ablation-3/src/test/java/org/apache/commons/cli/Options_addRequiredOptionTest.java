
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Options_addRequiredOptionTest {
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testAddRequiredOptionSuccess() {
        options.addRequiredOption("r", "required", true, "required option with argument");
        assertTrue(options.hasOption("r"));
        assertTrue(options.hasOption("required"));
        assertEquals(1, options.getRequiredOptions().size());
    }

    @Test
    public void testAddRequiredOptionNoArgument() {
        options.addRequiredOption("n", "noarg", false, "required option without argument");
        assertTrue(options.hasOption("n"));
        assertTrue(options.hasOption("noarg"));
        assertEquals(1, options.getRequiredOptions().size());
    }

    @Test
    public void testAddRequiredOptionDuplicate() {
        options.addRequiredOption("d", "duplicate", true, "first required option");
        options.addRequiredOption("d", "duplicate", true, "second required option");
        assertTrue(options.hasOption("d"));
        assertTrue(options.hasOption("duplicate"));
        assertEquals(1, options.getRequiredOptions().size());
    }
}
