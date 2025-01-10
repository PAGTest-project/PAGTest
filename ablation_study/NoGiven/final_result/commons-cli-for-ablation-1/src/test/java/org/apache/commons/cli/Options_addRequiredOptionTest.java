
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
    public void testAddRequiredOption() {
        options.addRequiredOption("f", "file", true, "Specify input file");

        assertTrue(options.hasOption("f"));
        assertTrue(options.hasOption("file"));
        assertTrue(options.hasLongOption("file"));
        assertTrue(options.hasShortOption("f"));
        assertEquals(1, options.getRequiredOptions().size());
    }

    @Test
    public void testAddRequiredOptionWithoutArgument() {
        options.addRequiredOption("v", "verbose", false, "Enable verbose mode");

        assertTrue(options.hasOption("v"));
        assertTrue(options.hasOption("verbose"));
        assertTrue(options.hasLongOption("verbose"));
        assertTrue(options.hasShortOption("v"));
        assertEquals(1, options.getRequiredOptions().size());
    }
}
