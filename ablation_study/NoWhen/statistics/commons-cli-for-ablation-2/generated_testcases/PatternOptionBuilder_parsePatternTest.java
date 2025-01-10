
package org.apache.commons.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatternOptionBuilder_parsePatternTest {

    @Test
    public void testParsePattern_SingleOption() {
        Options options = PatternOptionBuilder.parsePattern("a");
        assertEquals(1, options.getOptions().size());
        Option option = options.getOptions().iterator().next();
        assertEquals("a", option.getOpt());
        assertFalse(option.hasArg());
    }

    @Test
    public void testParsePattern_OptionWithValueCode() {
        Options options = PatternOptionBuilder.parsePattern("a@");
        assertEquals(1, options.getOptions().size());
        Option option = options.getOptions().iterator().next();
        assertEquals("a", option.getOpt());
        assertTrue(option.hasArg());
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, option.getType());
    }

    @Test
    public void testParsePattern_OptionWithRequiredFlag() {
        Options options = PatternOptionBuilder.parsePattern("a!");
        assertEquals(1, options.getOptions().size());
        Option option = options.getOptions().iterator().next();
        assertEquals("a", option.getOpt());
        assertFalse(option.hasArg());
        assertTrue(option.isRequired());
    }

    @Test
    public void testParsePattern_MultipleOptions() {
        Options options = PatternOptionBuilder.parsePattern("ab@c!");
        assertEquals(3, options.getOptions().size());
        Option optionA = options.getOption("a");
        Option optionB = options.getOption("b");
        Option optionC = options.getOption("c");

        assertNotNull(optionA);
        assertFalse(optionA.hasArg());

        assertNotNull(optionB);
        assertTrue(optionB.hasArg());
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, optionB.getType());

        assertNotNull(optionC);
        assertFalse(optionC.hasArg());
        assertTrue(optionC.isRequired());
    }
}
