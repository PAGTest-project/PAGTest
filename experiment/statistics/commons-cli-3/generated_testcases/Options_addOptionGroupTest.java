
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Option option1 = Option.builder("a").build();
        Option option2 = Option.builder("b").build();
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
        Option option1 = Option.builder("a").build();
        Option option2 = Option.builder("b").build();
        group.addOption(option1);
        group.addOption(option2);

        options.addOptionGroup(group);

        assertFalse(options.getRequiredOptions().contains(group));
        assertFalse(option1.isRequired());
        assertFalse(option2.isRequired());
        assertEquals(group, options.getOptionGroup(option1));
        assertEquals(group, options.getOptionGroup(option2));
    }
}
