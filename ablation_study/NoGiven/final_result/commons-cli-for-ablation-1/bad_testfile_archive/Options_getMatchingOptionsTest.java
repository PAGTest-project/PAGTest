
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import java.util.Arrays;

public class Options_getMatchingOptionsTest {
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testGetMatchingOptionsExactMatch() {
        options.addOption(new Option("a", "apple", false, "apple option"));
        options.addOption(new Option("b", "banana", false, "banana option"));

        assertEquals(Collections.singletonList("apple"), options.getMatchingOptions("apple"));
    }

    @Test
    public void testGetMatchingOptionsPartialMatch() {
        options.addOption(new Option("a", "apple", false, "apple option"));
        options.addOption(new Option("b", "banana", false, "banana option"));
        options.addOption(new Option("c", "cherry", false, "cherry option"));

        assertEquals(Arrays.asList("apple", "banana"), options.getMatchingOptions("a"));
    }

    @Test
    public void testGetMatchingOptionsNoMatch() {
        options.addOption(new Option("a", "apple", false, "apple option"));
        options.addOption(new Option("b", "banana", false, "banana option"));

        assertEquals(Collections.emptyList(), options.getMatchingOptions("z"));
    }

    @Test
    public void testGetMatchingOptionsWithHyphens() {
        options.addOption(new Option("a", "apple", false, "apple option"));
        options.addOption(new Option("b", "banana", false, "banana option"));

        assertEquals(Collections.singletonList("apple"), options.getMatchingOptions("--apple"));
    }

    @Test
    public void testGetMatchingOptionsWithMultipleHyphens() {
        options.addOption(new Option("a", "apple", false, "apple option"));
        options.addOption(new Option("b", "banana", false, "banana option"));

        assertEquals(Collections.singletonList("apple"), options.getMatchingOptions("---apple"));
    }
}
