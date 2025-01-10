
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collection;

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
        Option option = new Option("o", "option", false, "Option description");
        optionGroup.addOption(option);

        Collection<String> names = optionGroup.getNames();
        Collection<Option> options = optionGroup.getOptions();

        assertNotNull(names);
        assertNotNull(options);
        assertEquals(1, names.size());
        assertEquals(1, options.size());
        assertTrue(names.contains("o"));
        assertTrue(options.contains(option));
    }

    @Test
    public void testAddOptionWithRequiredState() {
        optionGroup.setRequired(true);
        Option option = new Option("o", "option", false, "Option description");
        optionGroup.addOption(option);

        assertTrue(optionGroup.isRequired());
        assertEquals(1, optionGroup.getNames().size());
        assertEquals(1, optionGroup.getOptions().size());
    }

    @Test
    public void testAddOptionWithSelectedState() {
        Option option1 = new Option("o1", "option1", false, "Option 1 description");
        Option option2 = new Option("o2", "option2", false, "Option 2 description");
        optionGroup.addOption(option1);
        optionGroup.setSelected(option1);

        assertEquals("o1", optionGroup.getSelected());

        optionGroup.addOption(option2);

        assertEquals(2, optionGroup.getNames().size());
        assertEquals(2, optionGroup.getOptions().size());
        assertEquals("o1", optionGroup.getSelected());
    }

    @Test
    public void testAddOptionToStringRepresentation() {
        Option option1 = new Option("o1", "option1", false, "Option 1 description");
        Option option2 = new Option("o2", "option2", false, "Option 2 description");
        optionGroup.addOption(option1);
        optionGroup.addOption(option2);

        String expectedString = "[-o1 Option 1 description, -o2 Option 2 description]";
        assertEquals(expectedString, optionGroup.toString());
    }
}
