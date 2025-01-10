
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
        newOptions.addOption("a", "alpha", false, "description");
        newOptions.addOption("b", "beta", false, "description");

        options.addOptions(newOptions);

        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
    }

    @Test
    public void testAddOptionsDuplicateKey() {
        Options newOptions = new Options();
        newOptions.addOption("a", "alpha", false, "description");
        newOptions.addOption("b", "beta", false, "description");

        options.addOption("a", "alpha", false, "description");

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
        newOptions.addOptionGroup(group1);

        options.addOptions(newOptions);

        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
        assertEquals(1, options.getOptionGroups().size());
    }
}
