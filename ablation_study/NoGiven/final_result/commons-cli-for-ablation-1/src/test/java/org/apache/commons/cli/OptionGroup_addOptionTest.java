
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        Option option = Option.builder("o").build();
        optionGroup.addOption(option);

        assertTrue(optionGroup.getNames().contains("o"));
        assertTrue(optionGroup.getOptions().contains(option));
    }

    @Test
    public void testAddOptionWithRequiredState() {
        optionGroup.setRequired(true);
        Option option = Option.builder("o").build();
        optionGroup.addOption(option);

        assertTrue(optionGroup.getNames().contains("o"));
        assertTrue(optionGroup.getOptions().contains(option));
        assertTrue(optionGroup.isRequired());
    }

    @Test
    public void testAddMultipleOptions() {
        Option option1 = Option.builder("o1").build();
        Option option2 = Option.builder("o2").build();
        optionGroup.addOption(option1);
        optionGroup.addOption(option2);

        assertEquals(2, optionGroup.getNames().size());
        assertTrue(optionGroup.getNames().contains("o1"));
        assertTrue(optionGroup.getNames().contains("o2"));
        assertTrue(optionGroup.getOptions().contains(option1));
        assertTrue(optionGroup.getOptions().contains(option2));
    }

    @Test
    public void testAddOptionWithNoOptionsInitially() {
        assertFalse(optionGroup.getNames().contains("o"));
        assertFalse(optionGroup.getOptions().contains(Option.builder("o").build()));

        Option option = Option.builder("o").build();
        optionGroup.addOption(option);

        assertTrue(optionGroup.getNames().contains("o"));
        assertTrue(optionGroup.getOptions().contains(option));
    }
}
