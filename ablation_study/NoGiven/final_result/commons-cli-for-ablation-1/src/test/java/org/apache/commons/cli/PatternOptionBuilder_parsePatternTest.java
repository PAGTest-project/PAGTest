
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import org.junit.jupiter.api.Test;

public class PatternOptionBuilder_parsePatternTest {

    @Test
    public void testParsePattern_SingleOption() {
        Options options = PatternOptionBuilder.parsePattern("a");
        Collection<Option> optionList = options.getOptions();

        assertEquals(1, optionList.size());
        Option option = optionList.iterator().next();
        assertEquals("a", option.getOpt());
        assertEquals(false, option.hasArg());
    }

    @Test
    public void testParsePattern_OptionWithType() {
        Options options = PatternOptionBuilder.parsePattern("a@");
        Collection<Option> optionList = options.getOptions();

        assertEquals(1, optionList.size());
        Option option = optionList.iterator().next();
        assertEquals("a", option.getOpt());
        assertEquals(true, option.hasArg());
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, option.getType());
    }

    @Test
    public void testParsePattern_OptionWithRequiredFlag() {
        Options options = PatternOptionBuilder.parsePattern("a!");
        Collection<Option> optionList = options.getOptions();

        assertEquals(1, optionList.size());
        Option option = optionList.iterator().next();
        assertEquals("a", option.getOpt());
        assertEquals(false, option.hasArg());
        assertEquals(true, option.isRequired());
    }

    @Test
    public void testParsePattern_MultipleOptions() {
        Options options = PatternOptionBuilder.parsePattern("ab@c!");
        Collection<Option> optionList = options.getOptions();

        assertEquals(3, optionList.size());

        Option optionA = options.getOption("a");
        assertEquals("a", optionA.getOpt());
        assertEquals(false, optionA.hasArg());

        Option optionB = options.getOption("b");
        assertEquals("b", optionB.getOpt());
        assertEquals(true, optionB.hasArg());
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, optionB.getType());

        Option optionC = options.getOption("c");
        assertEquals("c", optionC.getOpt());
        assertEquals(false, optionC.hasArg());
        assertEquals(true, optionC.isRequired());
    }
}
