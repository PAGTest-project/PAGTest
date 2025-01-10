
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

        assertThrows(IllegalArgumentException.class, () -> {
            options.addOptions(newOptions);
        });
    }
}
