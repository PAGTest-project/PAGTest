
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
    public void testAddRequiredOptionWithShortAndLongOpt() {
        options.addRequiredOption("f", "file", true, "Specify file");

        assertTrue(options.hasOption("f"));
        assertTrue(options.hasOption("file"));
        assertTrue(options.hasLongOption("file"));
        assertTrue(options.hasShortOption("f"));
        assertEquals(1, options.getRequiredOptions().size());
    }

    @Test
    public void testAddRequiredOptionWithoutArgument() {
        options.addRequiredOption("v", "verbose", false, "Verbose mode");

        assertTrue(options.hasOption("v"));
        assertTrue(options.hasOption("verbose"));
        assertTrue(options.hasLongOption("verbose"));
        assertTrue(options.hasShortOption("v"));
        assertEquals(1, options.getRequiredOptions().size());
    }

    @Test
    public void testAddRequiredOptionWithArgument() {
        options.addRequiredOption("o", "output", true, "Output file");

        assertTrue(options.hasOption("o"));
        assertTrue(options.hasOption("output"));
        assertTrue(options.hasLongOption("output"));
        assertTrue(options.hasShortOption("o"));
        assertEquals(1, options.getRequiredOptions().size());
    }
}
