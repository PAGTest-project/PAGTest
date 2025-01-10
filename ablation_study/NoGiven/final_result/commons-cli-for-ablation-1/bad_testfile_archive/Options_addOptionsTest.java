
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Options_addOptionsTest {
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
    }

    @Test
    public void testAddOptionsSuccess() {
        Options newOptions = new Options();
        newOptions.addOption("a", "optionA", false, "descriptionA");
        newOptions.addOption("b", "optionB", false, "descriptionB");

        options.addOptions(newOptions);

        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
    }

    @Test
    public void testAddOptionsDuplicateKey() {
        Options newOptions = new Options();
        newOptions.addOption("a", "optionA", false, "descriptionA");
        newOptions.addOption("a", "optionA", false, "descriptionA");

        assertThrows(IllegalArgumentException.class, () -> {
            options.addOptions(newOptions);
        });
    }

    @Test
    public void testAddOptionsWithOptionGroup() {
        Options newOptions = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", "optionA", false, "descriptionA"));
        group.addOption(new Option("b", "optionB", false, "descriptionB"));
        newOptions.addOptionGroup(group);

        options.addOptions(newOptions);

        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
        assertNotNull(options.getOptionGroup(new Option("a", false, "")));
    }

    @Test
    public void testAddOptionsEmpty() {
        Options newOptions = new Options();

        options.addOptions(newOptions);

        assertTrue(options.getOptions().isEmpty());
    }
}
