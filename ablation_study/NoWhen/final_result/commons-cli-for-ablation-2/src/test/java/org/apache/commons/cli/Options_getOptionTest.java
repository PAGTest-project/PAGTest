
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
        options.addOption("a", "first", false, "first option");
        Option option = options.getOption("a");
        assertNotNull(option);
        assertEquals("a", option.getOpt());
    }

    @Test
    public void testGetOptionLong() {
        options.addOption("b", "second", true, "second option");
        Option option = options.getOption("second");
        assertNotNull(option);
        assertEquals("second", option.getLongOpt());
    }

    @Test
    public void testGetOptionNotFound() {
        Option option = options.getOption("c");
        assertNull(option);
    }

    @Test
    public void testGetOptionWithHyphens() {
        options.addOption("d", "fourth", false, "fourth option");
        Option option = options.getOption("--fourth");
        assertNotNull(option);
        assertEquals("fourth", option.getLongOpt());
    }

    @Test
    public void testGetOptionMixedCase() {
        options.addOption("e", "fifth", false, "fifth option");
        Option option = options.getOption("fifth");
        assertNotNull(option);
        assertEquals("fifth", option.getLongOpt());
    }
}
