
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        options.addRequiredOption("a", "alpha", false, "description");
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("alpha"));
        assertTrue(options.getOption("a").isRequired());
        assertEquals("description", options.getOption("a").getDescription());
    }

    @Test
    public void testAddRequiredOptionWithArgument() {
        options.addRequiredOption("b", "beta", true, "description");
        assertTrue(options.hasOption("b"));
        assertTrue(options.hasOption("beta"));
        assertTrue(options.getOption("b").isRequired());
        assertTrue(options.getOption("b").hasArg());
        assertEquals("description", options.getOption("b").getDescription());
    }

    @Test
    public void testAddRequiredOptionWithoutArgument() {
        options.addRequiredOption("c", "gamma", false, "description");
        assertTrue(options.hasOption("c"));
        assertTrue(options.hasOption("gamma"));
        assertTrue(options.getOption("c").isRequired());
        assertFalse(options.getOption("c").hasArg());
        assertEquals("description", options.getOption("c").getDescription());
    }

    @Test
    public void testAddRequiredOptionDuplicate() {
        options.addRequiredOption("d", "delta", false, "description1");
        options.addRequiredOption("d", "delta", false, "description2");
        assertTrue(options.hasOption("d"));
        assertTrue(options.hasOption("delta"));
        assertTrue(options.getOption("d").isRequired());
        assertEquals("description2", options.getOption("d").getDescription());
    }
}
