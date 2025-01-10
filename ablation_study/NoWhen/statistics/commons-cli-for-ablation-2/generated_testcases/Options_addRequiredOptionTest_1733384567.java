
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
    public void testAddRequiredOption_ShortAndLongOpt_NoArg() {
        options.addRequiredOption("a", "alpha", false, "description");

        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("alpha"));
        assertTrue(options.hasLongOption("alpha"));
        assertTrue(options.hasShortOption("a"));
        assertTrue(options.getRequiredOptions().contains("a"));
    }

    @Test
    public void testAddRequiredOption_ShortAndLongOpt_WithArg() {
        options.addRequiredOption("b", "beta", true, "description");

        assertTrue(options.hasOption("b"));
        assertTrue(options.hasOption("beta"));
        assertTrue(options.hasLongOption("beta"));
        assertTrue(options.hasShortOption("b"));
        assertTrue(options.getRequiredOptions().contains("b"));
    }

    @Test
    public void testAddRequiredOption_ShortOptOnly_NoArg() {
        options.addRequiredOption("c", null, false, "description");

        assertTrue(options.hasOption("c"));
        assertFalse(options.hasLongOption("c"));
        assertTrue(options.hasShortOption("c"));
        assertTrue(options.getRequiredOptions().contains("c"));
    }

    @Test
    public void testAddRequiredOption_ShortOptOnly_WithArg() {
        options.addRequiredOption("d", null, true, "description");

        assertTrue(options.hasOption("d"));
        assertFalse(options.hasLongOption("d"));
        assertTrue(options.hasShortOption("d"));
        assertTrue(options.getRequiredOptions().contains("d"));
    }

    @Test
    public void testAddRequiredOption_LongOptOnly_NoArg() {
        options.addRequiredOption(null, "delta", false, "description");

        assertFalse(options.hasOption("delta"));
        assertFalse(options.hasShortOption("delta"));
        assertTrue(options.hasLongOption("delta"));
        assertTrue(options.getRequiredOptions().contains("delta"));
    }

    @Test
    public void testAddRequiredOption_LongOptOnly_WithArg() {
        options.addRequiredOption(null, "epsilon", true, "description");

        assertFalse(options.hasOption("epsilon"));
        assertFalse(options.hasShortOption("epsilon"));
        assertTrue(options.hasLongOption("epsilon"));
        assertTrue(options.getRequiredOptions().contains("epsilon"));
    }
}
