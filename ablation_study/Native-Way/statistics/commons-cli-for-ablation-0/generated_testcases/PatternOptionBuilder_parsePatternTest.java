
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatternOptionBuilder_parsePatternTest {

    @BeforeEach
    public void setUp() {
        // No setup required for static method
    }

    @Test
    public void testParsePatternSingleOption() {
        Options options = PatternOptionBuilder.parsePattern("a");
        assertNotNull(options);
        assertTrue(options.hasOption("a"));
    }

    @Test
    public void testParsePatternOptionWithType() {
        Options options = PatternOptionBuilder.parsePattern("a@");
        assertNotNull(options);
        assertTrue(options.hasOption("a"));
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, options.getOption("a").getType());
    }

    @Test
    public void testParsePatternOptionWithRequired() {
        Options options = PatternOptionBuilder.parsePattern("a!");
        assertNotNull(options);
        assertTrue(options.hasOption("a"));
        assertTrue(options.getOption("a").isRequired());
    }

    @Test
    public void testParsePatternMultipleOptions() {
        Options options = PatternOptionBuilder.parsePattern("ab@c!");
        assertNotNull(options);
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
        assertTrue(options.hasOption("c"));
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, options.getOption("b").getType());
        assertTrue(options.getOption("c").isRequired());
    }

    @Test
    public void testParsePatternEmpty() {
        Options options = PatternOptionBuilder.parsePattern("");
        assertNotNull(options);
        assertEquals(0, options.getOptions().size());
    }

    @Test
    public void testParsePatternUnsupportedType() {
        Options options = PatternOptionBuilder.parsePattern("a*");
        assertNotNull(options);
        assertTrue(options.hasOption("a"));
        assertEquals(PatternOptionBuilder.FILES_VALUE, options.getOption("a").getType());
    }
}
