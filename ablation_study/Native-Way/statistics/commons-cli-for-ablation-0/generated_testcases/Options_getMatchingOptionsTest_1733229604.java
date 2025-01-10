
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(Collections.singletonList("apple"), options.getMatchingOptions("apple"));
    }

    @Test
    public void testGetMatchingOptionsPartialMatch() {
        assertEquals(Arrays.asList("apple", "banana"), options.getMatchingOptions("a"));
    }

    @Test
    public void testGetMatchingOptionsNoMatch() {
        assertEquals(Collections.emptyList(), options.getMatchingOptions("z"));
    }

    @Test
    public void testGetMatchingOptionsEmptyInput() {
        assertEquals(Collections.emptyList(), options.getMatchingOptions(""));
    }

    @Test
    public void testGetMatchingOptionsNullInput() {
        assertEquals(Collections.emptyList(), options.getMatchingOptions(null));
    }
}
