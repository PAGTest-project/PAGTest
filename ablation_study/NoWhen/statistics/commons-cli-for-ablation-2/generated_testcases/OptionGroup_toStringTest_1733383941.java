
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OptionGroup_toStringTest {

    private OptionGroup optionGroup;

    @BeforeEach
    public void setUp() {
        optionGroup = new OptionGroup();
    }

    @Test
    public void testToStringWithNoOptions() {
        assertEquals("[]", optionGroup.toString());
    }

    @Test
    public void testToStringWithSingleOption() {
        Option option = new Option("a", "description for a");
        optionGroup.addOption(option);
        assertEquals("[-adescription for a]", optionGroup.toString().replace(" ", ""));
    }

    @Test
    public void testToStringWithMultipleOptions() {
        Option option1 = new Option("a", "description for a");
        Option option2 = new Option("b", "description for b");
        optionGroup.addOption(option1);
        optionGroup.addOption(option2);
        assertEquals("[-adescription for a, -bdescription for b]", optionGroup.toString().replace(" ", ""));
    }

    @Test
    public void testToStringWithLongOption() {
        Option option = new Option(null, "longOpt", false, "description for longOpt");
        optionGroup.addOption(option);
        assertEquals("[--longOptdescription for longOpt]", optionGroup.toString().replace(" ", ""));
    }

    @Test
    public void testToStringWithMixedOptions() {
        Option option1 = new Option("a", "description for a");
        Option option2 = new Option(null, "longOpt", false, "description for longOpt");
        optionGroup.addOption(option1);
        optionGroup.addOption(option2);
        assertEquals("[-adescription for a, --longOptdescription for longOpt]", optionGroup.toString().replace(" ", ""));
    }

    @Test
    public void testToStringWithOptionWithoutDescription() {
        Option option = new Option("a", null);
        optionGroup.addOption(option);
        assertEquals("[-a]", optionGroup.toString());
    }
}
