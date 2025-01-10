
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
        group.addOption(Option.builder("a").build());
        group.addOption(Option.builder("b").build());

        options.addOptionGroup(group);

        assertTrue(options.getRequiredOptions().contains(group));
        assertEquals(group, options.getOptionGroup(Option.builder("a").build()));
        assertEquals(group, options.getOptionGroup(Option.builder("b").build()));
    }

    @Test
    public void testAddOptionGroupWithNonRequiredGroup() {
        OptionGroup group = new OptionGroup();
        group.setRequired(false);
        group.addOption(Option.builder("x").build());
        group.addOption(Option.builder("y").build());

        options.addOptionGroup(group);

        assertFalse(options.getRequiredOptions().contains(group));
        assertEquals(group, options.getOptionGroup(Option.builder("x").build()));
        assertEquals(group, options.getOptionGroup(Option.builder("y").build()));
    }

    @Test
    public void testAddOptionGroupSetsOptionRequiredToFalse() {
        OptionGroup group = new OptionGroup();
        Option optionA = Option.builder("a").required(true).build();
        Option optionB = Option.builder("b").required(true).build();
        group.addOption(optionA);
        group.addOption(optionB);

        options.addOptionGroup(group);

        assertFalse(optionA.isRequired());
        assertFalse(optionB.isRequired());
    }

    @Test
    public void testAddOptionGroupAddsOptionsToShortOpts() {
        OptionGroup group = new OptionGroup();
        Option optionA = Option.builder("a").build();
        Option optionB = Option.builder("b").build();
        group.addOption(optionA);
        group.addOption(optionB);

        options.addOptionGroup(group);

        assertTrue(options.hasShortOption("a"));
        assertTrue(options.hasShortOption("b"));
    }

    @Test
    public void testAddOptionGroupAddsOptionsToLongOpts() {
        OptionGroup group = new OptionGroup();
        Option optionA = Option.builder("a").longOpt("alpha").build();
        Option optionB = Option.builder("b").longOpt("beta").build();
        group.addOption(optionA);
        group.addOption(optionB);

        options.addOptionGroup(group);

        assertTrue(options.hasLongOption("alpha"));
        assertTrue(options.hasLongOption("beta"));
    }
}
