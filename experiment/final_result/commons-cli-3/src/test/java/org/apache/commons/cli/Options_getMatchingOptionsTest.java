
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class Options_getMatchingOptionsTest {

    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
        options.addOption("a", "apple", false, "description for apple");
        options.addOption("b", "banana", false, "description for banana");
        options.addOption("c", "cherry", false, "description for cherry");
    }

    @Test
    public void testGetMatchingOptionsExactMatch() {
        List<String> matchingOptions = options.getMatchingOptions("apple");
        assertEquals(Collections.singletonList("apple"), matchingOptions);
    }

    @Test
    public void testGetMatchingOptionsPartialMatch() {
        List<String> matchingOptions = options.getMatchingOptions("ban");
        assertEquals(Collections.singletonList("banana"), matchingOptions);
    }

    @Test
    public void testGetMatchingOptionsNoMatch() {
        List<String> matchingOptions = options.getMatchingOptions("nonexistent");
        assertTrue(matchingOptions.isEmpty());
    }

    @Test
    public void testGetMatchingOptionsLeadingHyphens() {
        List<String> matchingOptions = options.getMatchingOptions("--cherry");
        assertEquals(Collections.singletonList("cherry"), matchingOptions);
    }
}
