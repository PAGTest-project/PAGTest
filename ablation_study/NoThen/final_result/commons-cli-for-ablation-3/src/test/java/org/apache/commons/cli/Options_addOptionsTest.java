
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        newOptions.addOption("a", "optionA", false, "Description A");
        newOptions.addOption("b", "optionB", true, "Description B");

        options.addOptions(newOptions);

        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
    }

    @Test
    public void testAddOptionsDuplicateKey() {
        Options newOptions = new Options();
        newOptions.addOption("a", "optionA", false, "Description A");
        newOptions.addOption("b", "optionB", true, "Description B");

        options.addOption("a", "optionA", false, "Description A");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            options.addOptions(newOptions);
        });

        assertEquals("Duplicate key: a", exception.getMessage());
    }

    @Test
    public void testAddOptionsWithOptionGroups() {
        Options newOptions = new Options();
        OptionGroup group1 = new OptionGroup();
        group1.addOption(OptionBuilder.create('a'));
        group1.addOption(OptionBuilder.create('b'));
        newOptions.addOptionGroup(group1);

        options.addOptions(newOptions);

        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
        assertEquals(1, options.getOptionGroups().size());
    }
}
