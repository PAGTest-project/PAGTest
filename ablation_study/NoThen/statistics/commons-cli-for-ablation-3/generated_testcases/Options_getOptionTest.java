
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Options_getOptionTest {
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testGetOptionShort() {
        Option option = new Option("s", "shortOption", false, "Short option description");
        options.addOption(option);

        Option retrievedOption = options.getOption("s");
        assertEquals(option, retrievedOption);
    }

    @Test
    public void testGetOptionLong() {
        Option option = new Option("l", "longOption", false, "Long option description");
        options.addOption(option);

        Option retrievedOption = options.getOption("longOption");
        assertEquals(option, retrievedOption);
    }

    @Test
    public void testGetOptionNonexistent() {
        Option retrievedOption = options.getOption("nonexistent");
        assertNull(retrievedOption);
    }

    @Test
    public void testGetOptionWithHyphens() {
        Option option = new Option("h", "hyphenOption", false, "Option with hyphens description");
        options.addOption(option);

        Option retrievedOption = options.getOption("--hyphenOption");
        assertEquals(option, retrievedOption);

        retrievedOption = options.getOption("-h");
        assertEquals(option, retrievedOption);
    }

    @Test
    public void testGetOptionMixedCase() {
        Option option = new Option("m", "mixedCase", false, "Mixed case option description");
        options.addOption(option);

        Option retrievedOption = options.getOption("mixedCase");
        assertEquals(option, retrievedOption);

        retrievedOption = options.getOption("m");
        assertEquals(option, retrievedOption);
    }
}
