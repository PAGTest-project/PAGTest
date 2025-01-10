
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
    public void testAddOptionGroupWithRequiredGroup() {
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        Option option1 = new Option("o1", "option1");
        Option option2 = new Option("o2", "option2");
        group.addOption(option1);
        group.addOption(option2);

        options.addOptionGroup(group);

        assertTrue(options.getRequiredOptions().contains(group));
        assertFalse(option1.isRequired());
        assertFalse(option2.isRequired());
        assertEquals(group, options.getOptionGroup(option1));
        assertEquals(group, options.getOptionGroup(option2));
    }

    @Test
    public void testAddOptionGroupWithNonRequiredGroup() {
        OptionGroup group = new OptionGroup();
        group.setRequired(false);
        Option option1 = new Option("o1", "option1");
        Option option2 = new Option("o2", "option2");
        group.addOption(option1);
        group.addOption(option2);

        options.addOptionGroup(group);

        assertFalse(options.getRequiredOptions().contains(group));
        assertFalse(option1.isRequired());
        assertFalse(option2.isRequired());
        assertEquals(group, options.getOptionGroup(option1));
        assertEquals(group, options.getOptionGroup(option2));
    }

    @Test
    public void testAddOptionGroupWithEmptyGroup() {
        OptionGroup group = new OptionGroup();
        group.setRequired(true);

        options.addOptionGroup(group);

        assertTrue(options.getRequiredOptions().contains(group));
        assertTrue(group.getOptions().isEmpty());
    }

    @Test
    public void testAddOptionGroupWithDuplicateOptions() {
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        Option option1 = new Option("o1", "option1");
        Option option2 = new Option("o1", "option1");
        group.addOption(option1);
        group.addOption(option2);

        options.addOptionGroup(group);

        assertTrue(options.getRequiredOptions().contains(group));
        assertFalse(option1.isRequired());
        assertFalse(option2.isRequired());
        assertEquals(group, options.getOptionGroup(option1));
        assertEquals(group, options.getOptionGroup(option2));
    }
}
