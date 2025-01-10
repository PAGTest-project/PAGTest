
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
        options.addOption("s", "short", false, "short option");
        Option option = options.getOption("s");
        assertEquals("s", option.getOpt());
    }

    @Test
    public void testGetOptionLong() {
        options.addOption("l", "long", false, "long option");
        Option option = options.getOption("long");
        assertEquals("long", option.getLongOpt());
    }

    @Test
    public void testGetOptionNonexistent() {
        Option option = options.getOption("nonexistent");
        assertNull(option);
    }
}
