
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Options_addOptionTest {
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testAddOptionWithLongOpt() {
        Option opt = new Option("a", "apple", false, "an apple");
        options.addOption(opt);
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasLongOption("apple"));
        assertEquals(opt, options.getOption("apple"));
    }

    @Test
    public void testAddOptionWithoutLongOpt() {
        Option opt = new Option("b", false, "a banana");
        options.addOption(opt);
        assertTrue(options.hasOption("b"));
        assertFalse(options.hasLongOption("banana"));
        assertEquals(opt, options.getOption("b"));
    }

    @Test
    public void testAddOptionRequired() {
        Option opt = new Option("c", "cherry", true, "a cherry");
        opt.setRequired(true);
        options.addOption(opt);
        assertTrue(options.hasOption("c"));
        assertTrue(options.hasLongOption("cherry"));
        assertTrue(options.getOption("cherry").isRequired());
    }

    @Test
    public void testAddOptionRequiredTwice() {
        Option opt = new Option("d", "date", true, "a date");
        opt.setRequired(true);
        options.addOption(opt);
        options.addOption(opt);
        assertTrue(options.hasOption("d"));
        assertTrue(options.hasLongOption("date"));
        assertTrue(options.getOption("date").isRequired());
        assertEquals(1, options.getRequiredOptions().size());
    }
}
