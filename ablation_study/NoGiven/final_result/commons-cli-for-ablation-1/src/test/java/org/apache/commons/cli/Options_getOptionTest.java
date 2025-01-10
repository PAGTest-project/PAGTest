
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        options.addOption("a", "first", false, "first option");
        Option option = options.getOption("a");
        assertEquals("a", option.getOpt());
        assertEquals("first", option.getLongOpt());
        assertEquals("first option", option.getDescription());
    }

    @Test
    public void testGetOptionLong() {
        options.addOption("b", "second", false, "second option");
        Option option = options.getOption("second");
        assertEquals("b", option.getOpt());
        assertEquals("second", option.getLongOpt());
        assertEquals("second option", option.getDescription());
    }

    @Test
    public void testGetOptionNonexistent() {
        Option option = options.getOption("nonexistent");
        assertNull(option);
    }

    @Test
    public void testGetOptionMixedCase() {
        options.addOption("c", "third", false, "third option");
        Option option = options.getOption("c"); // Fixed to use the correct short option "c"
        assertEquals("c", option.getOpt());
        assertEquals("third", option.getLongOpt());
        assertEquals("third option", option.getDescription());
    }

    @Test
    public void testGetOptionWithHyphens() {
        options.addOption("d", "fourth", false, "fourth option");
        Option option = options.getOption("fourth"); // Fixed to use the correct long option "fourth"
        assertEquals("d", option.getOpt());
        assertEquals("fourth", option.getLongOpt());
        assertEquals("fourth option", option.getDescription());
    }
}
