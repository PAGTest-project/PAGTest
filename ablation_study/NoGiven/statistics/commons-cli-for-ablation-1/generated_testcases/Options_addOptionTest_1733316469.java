
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
        Option opt = Option.builder("a").longOpt("apple").build();
        options.addOption(opt);
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasLongOption("apple"));
        assertEquals(opt, options.getOption("a"));
        assertEquals(opt, options.getOption("apple"));
    }

    @Test
    public void testAddOptionWithoutLongOpt() {
        Option opt = Option.builder("b").build();
        options.addOption(opt);
        assertTrue(options.hasOption("b"));
        assertFalse(options.hasLongOption("banana"));
        assertEquals(opt, options.getOption("b"));
    }

    @Test
    public void testAddRequiredOption() {
        Option opt = Option.builder("c").longOpt("cat").required().build();
        options.addOption(opt);
        assertTrue(options.hasOption("c"));
        assertTrue(options.hasLongOption("cat"));
        assertTrue(options.getRequiredOptions().contains("c"));
        assertEquals(opt, options.getOption("c"));
        assertEquals(opt, options.getOption("cat"));
    }

    @Test
    public void testAddOptionTwice() {
        Option opt1 = Option.builder("d").longOpt("dog").build();
        Option opt2 = Option.builder("d").longOpt("dog").build();
        options.addOption(opt1);
        options.addOption(opt2);
        assertTrue(options.hasOption("d"));
        assertTrue(options.hasLongOption("dog"));
        assertEquals(1, options.getOptions().size());
        assertEquals(opt2, options.getOption("d"));
        assertEquals(opt2, options.getOption("dog"));
    }

    @Test
    public void testAddOptionWithRequiredTwice() {
        Option opt1 = Option.builder("e").longOpt("elephant").required().build();
        Option opt2 = Option.builder("e").longOpt("elephant").required().build();
        options.addOption(opt1);
        options.addOption(opt2);
        assertTrue(options.hasOption("e"));
        assertTrue(options.hasLongOption("elephant"));
        assertTrue(options.getRequiredOptions().contains("e"));
        assertEquals(1, options.getOptions().size());
        assertEquals(opt2, options.getOption("e"));
        assertEquals(opt2, options.getOption("elephant"));
    }
}
