
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Options_addOptionTest {
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testAddOptionWithShortAndLongOpt() {
        Option opt = new Option("a", "apple", false, "description");
        options.addOption(opt);

        assertTrue(options.hasOption("a"));
        assertTrue(options.hasLongOption("apple"));
        assertEquals(opt, options.getOption("a"));
        assertEquals(opt, options.getOption("apple"));
    }

    @Test
    public void testAddOptionWithRequired() {
        Option opt = new Option("b", "banana", true, "description");
        opt.setRequired(true);
        options.addOption(opt);

        assertTrue(options.hasOption("b"));
        assertTrue(options.hasLongOption("banana"));
        assertTrue(options.getRequiredOptions().contains("b"));
    }

    @Test
    public void testAddOptionWithoutLongOpt() {
        Option opt = new Option("c", false, "description");
        options.addOption(opt);

        assertTrue(options.hasOption("c"));
        assertFalse(options.hasLongOption("c"));
        assertEquals(opt, options.getOption("c"));
    }

    @Test
    public void testAddOptionWithDuplicateKey() {
        Option opt1 = new Option("d", "date", false, "description1");
        Option opt2 = new Option("d", "date", false, "description2");
        options.addOption(opt1);
        options.addOption(opt2);

        assertTrue(options.hasOption("d"));
        assertTrue(options.hasLongOption("date"));
        assertEquals(opt2, options.getOption("d"));
        assertEquals(opt2, options.getOption("date"));
    }

    @Test
    public void testAddOptionWithRequiredAndDuplicateKey() {
        Option opt1 = new Option("e", "egg", false, "description1");
        opt1.setRequired(true);
        Option opt2 = new Option("e", "egg", false, "description2");
        opt2.setRequired(true);
        options.addOption(opt1);
        options.addOption(opt2);

        assertTrue(options.hasOption("e"));
        assertTrue(options.hasLongOption("egg"));
        assertTrue(options.getRequiredOptions().contains("e"));
        assertEquals(opt2, options.getOption("e"));
        assertEquals(opt2, options.getOption("egg"));
    }
}
