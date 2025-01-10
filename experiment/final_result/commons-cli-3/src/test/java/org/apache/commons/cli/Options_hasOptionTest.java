
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Options_hasOptionTest {
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testHasOptionWithShortOption() {
        options.addOption("s", "shortOpt", false, "A short option");
        assertTrue(options.hasOption("s"));
    }

    @Test
    public void testHasOptionWithLongOption() {
        options.addOption("l", "longOpt", false, "A long option");
        assertTrue(options.hasOption("longOpt"));
    }

    @Test
    public void testHasOptionWithNonExistentOption() {
        options.addOption("s", "shortOpt", false, "A short option");
        assertFalse(options.hasOption("nonexistent"));
    }

    @Test
    public void testHasOptionWithHyphens() {
        options.addOption("s", "shortOpt", false, "A short option");
        assertTrue(options.hasOption("--shortOpt"));
        assertTrue(options.hasOption("-s"));
    }
}
