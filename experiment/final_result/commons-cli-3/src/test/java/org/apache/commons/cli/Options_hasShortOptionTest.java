
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Options_hasShortOptionTest {
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testHasShortOption_ExistingOption() {
        options.addOption("a", "first");
        assertTrue(options.hasShortOption("a"));
    }

    @Test
    public void testHasShortOption_NonExistingOption() {
        options.addOption("a", "first");
        assertFalse(options.hasShortOption("b"));
    }

    @Test
    public void testHasShortOption_WithLeadingHyphens() {
        options.addOption("a", "first");
        assertTrue(options.hasShortOption("--a"));
    }
}
