
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OptionGroup_addOptionTest {

    private OptionGroup optionGroup;

    @BeforeEach
    public void setUp() {
        optionGroup = new OptionGroup();
    }

    @Test
    public void testAddOption() {
        Option option = new Option("opt", "Option description");
        optionGroup.addOption(option);

        assertTrue(optionGroup.getNames().contains("opt"));
        assertTrue(optionGroup.getOptions().contains(option));
    }

    @Test
    public void testAddMultipleOptions() {
        Option option1 = new Option("opt1", "Option 1 description");
        Option option2 = new Option("opt2", "Option 2 description");

        optionGroup.addOption(option1);
        optionGroup.addOption(option2);

        assertEquals(2, optionGroup.getNames().size());
        assertTrue(optionGroup.getNames().contains("opt1"));
        assertTrue(optionGroup.getNames().contains("opt2"));

        assertEquals(2, optionGroup.getOptions().size());
        assertTrue(optionGroup.getOptions().contains(option1));
        assertTrue(optionGroup.getOptions().contains(option2));
    }
}
