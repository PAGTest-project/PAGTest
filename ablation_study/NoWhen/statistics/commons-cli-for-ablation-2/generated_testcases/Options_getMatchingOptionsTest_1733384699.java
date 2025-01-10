
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
        options.addOption("a", "apple", false, "an apple");
        options.addOption("b", "banana", false, "a banana");

        assertEquals(Collections.singletonList("apple"), options.getMatchingOptions("apple"));
    }

    @Test
    public void testGetMatchingOptionsPartialMatch() {
        options.addOption("a", "apple", false, "an apple");
        options.addOption("b", "banana", false, "a banana");
        options.addOption("c", "cherry", false, "a cherry");

        assertEquals(Arrays.asList("apple", "apricot"), options.getMatchingOptions("ap"));
    }

    @Test
    public void testGetMatchingOptionsNoMatch() {
        options.addOption("a", "apple", false, "an apple");
        options.addOption("b", "banana", false, "a banana");

        assertEquals(Collections.emptyList(), options.getMatchingOptions("cherry"));
    }

    @Test
    public void testGetMatchingOptionsLeadingHyphens() {
        options.addOption("a", "apple", false, "an apple");
        options.addOption("b", "banana", false, "a banana");

        assertEquals(Collections.singletonList("apple"), options.getMatchingOptions("--apple"));
    }

    @Test
    public void testGetMatchingOptionsMultipleLeadingHyphens() {
        options.addOption("a", "apple", false, "an apple");
        options.addOption("b", "banana", false, "a banana");

        assertEquals(Collections.singletonList("apple"), options.getMatchingOptions("---apple"));
    }
}
