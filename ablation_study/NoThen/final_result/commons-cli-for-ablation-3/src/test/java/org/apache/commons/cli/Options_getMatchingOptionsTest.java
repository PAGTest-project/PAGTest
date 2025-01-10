
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;

public class Options_getMatchingOptionsTest {
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testGetMatchingOptionsExactMatch() {
        options.addOption("a", "apple", false, "apple option");
        List<String> matchingOptions = options.getMatchingOptions("apple");
        assertEquals(1, matchingOptions.size());
        assertTrue(matchingOptions.contains("apple"));
    }

    @Test
    public void testGetMatchingOptionsPartialMatch() {
        options.addOption("b", "banana", false, "banana option");
        options.addOption("c", "cherry", false, "cherry option");
        List<String> matchingOptions = options.getMatchingOptions("ban");
        assertEquals(1, matchingOptions.size());
        assertTrue(matchingOptions.contains("banana"));
    }

    @Test
    public void testGetMatchingOptionsNoMatch() {
        options.addOption("d", "date", false, "date option");
        List<String> matchingOptions = options.getMatchingOptions("fig");
        assertTrue(matchingOptions.isEmpty());
    }

    @Test
    public void testGetMatchingOptionsMultipleMatches() {
        options.addOption("e", "elephant", false, "elephant option");
        options.addOption("f", "elephantine", false, "elephantine option");
        List<String> matchingOptions = options.getMatchingOptions("ele");
        assertEquals(2, matchingOptions.size());
        assertTrue(matchingOptions.contains("elephant"));
        assertTrue(matchingOptions.contains("elephantine"));
    }
}
