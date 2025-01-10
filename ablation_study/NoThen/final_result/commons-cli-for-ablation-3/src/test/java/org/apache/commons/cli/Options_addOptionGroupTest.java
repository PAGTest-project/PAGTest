
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Options_addOptionGroupTest {
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testAddOptionGroupRequired() {
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        Option option1 = new Option("a", "optionA");
        Option option2 = new Option("b", "optionB");
        group.addOption(option1);
        group.addOption(option2);

        options.addOptionGroup(group);

        assertTrue(options.getRequiredOptions().contains(group));
        assertEquals(group, options.getOptionGroup(option1));
        assertEquals(group, options.getOptionGroup(option2));
        assertFalse(option1.isRequired());
        assertFalse(option2.isRequired());
    }

    @Test
    public void testAddOptionGroupNotRequired() {
        OptionGroup group = new OptionGroup();
        group.setRequired(false);
        Option option1 = new Option("a", "optionA");
        Option option2 = new Option("b", "optionB");
        group.addOption(option1);
        group.addOption(option2);

        options.addOptionGroup(group);

        assertFalse(options.getRequiredOptions().contains(group));
        assertEquals(group, options.getOptionGroup(option1));
        assertEquals(group, options.getOptionGroup(option2));
        assertFalse(option1.isRequired());
        assertFalse(option2.isRequired());
    }

    @Test
    public void testAddOptionGroupWithExistingOptions() {
        Option option1 = new Option("a", "optionA");
        Option option2 = new Option("b", "optionB");
        options.addOption(option1);
        options.addOption(option2);

        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(option1);
        group.addOption(option2);

        options.addOptionGroup(group);

        assertTrue(options.getRequiredOptions().contains(group));
        assertEquals(group, options.getOptionGroup(option1));
        assertEquals(group, options.getOptionGroup(option2));
        assertFalse(option1.isRequired());
        assertFalse(option2.isRequired());
    }
}
