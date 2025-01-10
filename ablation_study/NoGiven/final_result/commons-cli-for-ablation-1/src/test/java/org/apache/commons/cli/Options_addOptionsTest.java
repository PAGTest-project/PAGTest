
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Options_addOptionsTest {

    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testAddOptionsSuccess() {
        Options newOptions = new Options();
        newOptions.addOption("a", "optionA", false, "Option A");
        newOptions.addOption("b", "optionB", false, "Option B");

        options.addOptions(newOptions);

        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
    }

    @Test
    public void testAddOptionsDuplicateKey() {
        Options newOptions = new Options();
        newOptions.addOption("a", "optionA", false, "Option A");
        newOptions.addOption("b", "optionB", false, "Option B");

        options.addOption("a", "optionA", false, "Option A");

        assertThrows(IllegalArgumentException.class, () -> {
            options.addOptions(newOptions);
        });
    }

    @Test
    public void testAddOptionsWithOptionGroups() {
        Options newOptions = new Options();
        OptionGroup group1 = new OptionGroup();
        group1.addOption(OptionBuilder.create('a'));
        group1.addOption(OptionBuilder.create('b'));

        OptionGroup group2 = new OptionGroup();
        group2.addOption(OptionBuilder.create('x'));
        group2.addOption(OptionBuilder.create('y'));

        newOptions.addOptionGroup(group1);
        newOptions.addOptionGroup(group2);

        options.addOptions(newOptions);

        assertEquals(2, options.getOptionGroups().size());
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
        assertTrue(options.hasOption("x"));
        assertTrue(options.hasOption("y"));
    }

    @Test
    public void testAddOptionsWithExistingOptionGroups() {
        Options newOptions = new Options();
        OptionGroup group1 = new OptionGroup();
        group1.addOption(OptionBuilder.create('a'));
        group1.addOption(OptionBuilder.create('b'));

        OptionGroup group2 = new OptionGroup();
        group2.addOption(OptionBuilder.create('x'));
        group2.addOption(OptionBuilder.create('y'));

        newOptions.addOptionGroup(group1);
        newOptions.addOptionGroup(group2);

        options.addOptionGroup(group1);

        assertThrows(IllegalArgumentException.class, () -> {
            options.addOptions(newOptions);
        });
    }
}
