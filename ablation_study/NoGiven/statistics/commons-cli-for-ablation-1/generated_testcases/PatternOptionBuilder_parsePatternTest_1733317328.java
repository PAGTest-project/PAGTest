
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PatternOptionBuilder_parsePatternTest {

    @Test
    public void testParsePattern_SingleOption() {
        Options options = PatternOptionBuilder.parsePattern("a");
        assertEquals(1, options.getOptions().size());
        Option option = options.getOption("a");
        assertEquals("a", option.getOpt());
        assertFalse(option.hasArg());
    }

    @Test
    public void testParsePattern_OptionWithValueCode() {
        Options options = PatternOptionBuilder.parsePattern("a@");
        assertEquals(1, options.getOptions().size());
        Option option = options.getOption("a");
        assertEquals("a", option.getOpt());
        assertTrue(option.hasArg());
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, option.getType());
    }

    @Test
    public void testParsePattern_OptionWithRequiredFlag() {
        Options options = PatternOptionBuilder.parsePattern("a!");
        assertEquals(1, options.getOptions().size());
        Option option = options.getOption("a");
        assertEquals("a", option.getOpt());
        assertFalse(option.hasArg());
        assertTrue(option.isRequired());
    }

    @Test
    public void testParsePattern_MultipleOptions() {
        Options options = PatternOptionBuilder.parsePattern("ab@c!");
        assertEquals(3, options.getOptions().size());

        Option optionA = options.getOption("a");
        assertEquals("a", optionA.getOpt());
        assertFalse(optionA.hasArg());

        Option optionB = options.getOption("b");
        assertEquals("b", optionB.getOpt());
        assertTrue(optionB.hasArg());
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, optionB.getType());

        Option optionC = options.getOption("c");
        assertEquals("c", optionC.getOpt());
        assertFalse(optionC.hasArg());
        assertTrue(optionC.isRequired());
    }
}
