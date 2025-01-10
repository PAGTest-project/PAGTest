
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        Option opt = new Option("a", "apple", false, "description");
        options.addOption(opt);

        assertTrue(options.hasOption("a"));
        assertTrue(options.hasLongOption("apple"));
        assertEquals(opt, options.getOption("a"));
        assertEquals(opt, options.getOption("apple"));
    }

    @Test
    public void testAddOptionWithoutLongOpt() {
        Option opt = new Option("b", false, "description");
        options.addOption(opt);

        assertTrue(options.hasOption("b"));
        assertEquals(opt, options.getOption("b"));
    }

    @Test
    public void testAddRequiredOption() {
        Option opt = new Option("c", "cat", true, "description");
        options.addOption(opt);

        assertTrue(options.hasOption("c"));
        assertTrue(options.hasLongOption("cat"));
        assertEquals(opt, options.getOption("c"));
        assertEquals(opt, options.getOption("cat"));
        assertTrue(options.getRequiredOptions().contains("c"));
    }

    @Test
    public void testAddOptionTwice() {
        Option opt1 = new Option("d", "dog", false, "description1");
        Option opt2 = new Option("d", "dog", false, "description2");
        options.addOption(opt1);
        options.addOption(opt2);

        assertTrue(options.hasOption("d"));
        assertTrue(options.hasLongOption("dog"));
        assertEquals(opt2, options.getOption("d"));
        assertEquals(opt2, options.getOption("dog"));
    }
}
