
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Options_getOptionTest {
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testGetOptionShort() {
        options.addOption("s", "shortOpt", false, "Short option");
        Option option = options.getOption("s");
        assertNotNull(option);
        assertEquals("s", option.getOpt());
    }

    @Test
    public void testGetOptionLong() {
        options.addOption("l", "longOpt", false, "Long option");
        Option option = options.getOption("longOpt");
        assertNotNull(option);
        assertEquals("longOpt", option.getLongOpt());
    }

    @Test
    public void testGetOptionNonexistent() {
        Option option = options.getOption("nonexistent");
        assertNull(option);
    }

    @Test
    public void testGetOptionWithHyphens() {
        options.addOption("h", "hyphenOpt", false, "Option with hyphens");
        Option option = options.getOption("--hyphenOpt");
        assertNotNull(option);
        assertEquals("hyphenOpt", option.getLongOpt());
    }

    @Test
    public void testGetOptionMixedCase() {
        options.addOption("m", "mixedCaseOpt", false, "Mixed case option");
        Option option = options.getOption("MixedCaseOpt");
        assertNotNull(option);
        assertEquals("mixedCaseOpt", option.getLongOpt());
    }
}
