
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Options_addOptionTest {
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testAddOptionWithLongOpt() {
        Option option = new Option("o", "option", false, "description");
        options.addOption(option);
        assertTrue(options.hasOption("o"));
        assertTrue(options.hasLongOption("option"));
    }

    @Test
    public void testAddOptionWithoutLongOpt() {
        Option option = new Option("o", false, "description");
        options.addOption(option);
        assertTrue(options.hasOption("o"));
        assertFalse(options.hasLongOption("option"));
    }

    @Test
    public void testAddRequiredOption() {
        Option option = new Option("o", "option", false, "description");
        option.setRequired(true);
        options.addOption(option);
        assertTrue(options.hasOption("o"));
        assertTrue(options.hasLongOption("option"));
        assertTrue(options.getRequiredOptions().contains("o"));
    }

    @Test
    public void testAddOptionTwice() {
        Option option1 = new Option("o", "option", false, "description");
        Option option2 = new Option("o", "option", false, "description");
        options.addOption(option1);
        options.addOption(option2);
        assertTrue(options.hasOption("o"));
        assertTrue(options.hasLongOption("option"));
        assertEquals(1, options.getRequiredOptions().size());
    }

    @Test
    public void testAddOptionWithRequiredAndLongOpt() {
        Option option = new Option("o", "option", false, "description");
        option.setRequired(true);
        options.addOption(option);
        assertTrue(options.hasOption("o"));
        assertTrue(options.hasLongOption("option"));
        assertTrue(options.getRequiredOptions().contains("o"));
    }

    @Test
    public void testAddOptionWithoutRequiredAndLongOpt() {
        Option option = new Option("o", false, "description");
        options.addOption(option);
        assertTrue(options.hasOption("o"));
        assertFalse(options.hasLongOption("option"));
        assertFalse(options.getRequiredOptions().contains("o"));
    }
}
