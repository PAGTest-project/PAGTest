
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Options_getMatchingOptionsTest {
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testGetMatchingOptionsExactMatch() {
        options.addOption(new Option("d", "debug", false, "Turn on debug."));
        options.addOption(new Option("e", "extract", false, "Turn on extract."));

        assertEquals(Collections.singletonList("debug"), options.getMatchingOptions("debug"));
    }

    @Test
    public void testGetMatchingOptionsPartialMatch() {
        options.addOption(new Option("d", "debug", false, "Turn on debug."));
        options.addOption(new Option("e", "extract", false, "Turn on extract."));

        assertEquals(Collections.singletonList("debug"), options.getMatchingOptions("deb"));
    }

    @Test
    public void testGetMatchingOptionsNoMatch() {
        options.addOption(new Option("d", "debug", false, "Turn on debug."));
        options.addOption(new Option("e", "extract", false, "Turn on extract."));

        assertEquals(Collections.emptyList(), options.getMatchingOptions("nonexistent"));
    }

    @Test
    public void testGetMatchingOptionsMultipleMatches() {
        options.addOption(new Option("d", "debug", false, "Turn on debug."));
        options.addOption(new Option("e", "extract", false, "Turn on extract."));
        options.addOption(new Option("de", "detailed", false, "Turn on detailed."));

        assertEquals(Arrays.asList("debug", "detailed"), options.getMatchingOptions("de"));
    }

    @Test
    public void testGetMatchingOptionsWithHyphens() {
        options.addOption(new Option("d", "debug", false, "Turn on debug."));
        options.addOption(new Option("e", "extract", false, "Turn on extract."));

        assertEquals(Collections.singletonList("debug"), options.getMatchingOptions("--debug"));
    }
}
