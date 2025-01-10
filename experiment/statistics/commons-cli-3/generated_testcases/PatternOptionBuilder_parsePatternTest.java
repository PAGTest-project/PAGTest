
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PatternOptionBuilder_parsePatternTest {

    @Test
    public void testParsePatternSingleOption() {
        final String pattern = "a";
        final Options options = PatternOptionBuilder.parsePattern(pattern);

        assertEquals(1, options.getOptions().size());
        assertTrue(options.hasOption("a"));
    }

    @Test
    public void testParsePatternOptionWithType() {
        final String pattern = "a@";
        final Options options = PatternOptionBuilder.parsePattern(pattern);

        assertEquals(1, options.getOptions().size());
        assertTrue(options.hasOption("a"));
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, options.getOption("a").getType());
    }

    @Test
    public void testParsePatternOptionWithRequired() {
        final String pattern = "a!";
        final Options options = PatternOptionBuilder.parsePattern(pattern);

        assertEquals(1, options.getOptions().size());
        assertTrue(options.hasOption("a"));
        assertTrue(options.getOption("a").isRequired());
    }

    @Test
    public void testParsePatternMultipleOptions() {
        final String pattern = "ab@c!";
        final Options options = PatternOptionBuilder.parsePattern(pattern);

        assertEquals(3, options.getOptions().size());
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
        assertTrue(options.hasOption("c"));
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, options.getOption("b").getType());
        assertTrue(options.getOption("c").isRequired());
    }
}
