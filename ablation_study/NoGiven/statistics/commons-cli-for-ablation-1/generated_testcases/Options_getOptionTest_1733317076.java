
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
        options.addOption("a", "first", false, "toggle -a");
        Option option = options.getOption("a");
        assertEquals("a", option.getOpt());
        assertEquals("first", option.getLongOpt());
    }

    @Test
    public void testGetOptionLong() {
        options.addOption("b", "second", true, "set -b");
        Option option = options.getOption("second");
        assertEquals("b", option.getOpt());
        assertEquals("second", option.getLongOpt());
    }

    @Test
    public void testGetOptionNonexistent() {
        Option option = options.getOption("nonexistent");
        assertNull(option);
    }

    @Test
    public void testGetOptionWithHyphens() {
        options.addOption("c", "third", false, "toggle -c");
        Option option = options.getOption("third");
        assertEquals("c", option.getOpt());
        assertEquals("third", option.getLongOpt());
    }
}
