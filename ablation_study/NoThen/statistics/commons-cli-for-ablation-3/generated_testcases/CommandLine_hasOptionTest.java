
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandLine_hasOptionTest {

    private CommandLine commandLine;

    @BeforeEach
    public void setUp() {
        commandLine = new CommandLine.Builder().build();
    }

    @Test
    public void testHasOption_OptionExists() {
        Option option = Option.builder("T").build();
        commandLine.addOption(option);
        assertTrue(commandLine.hasOption(option));
    }

    @Test
    public void testHasOption_OptionDoesNotExist() {
        Option option = Option.builder("T").build();
        assertFalse(commandLine.hasOption(option));
    }

    @Test
    public void testHasOption_DeprecatedOption() {
        Option option = Option.builder("D").deprecated(true).build();
        commandLine.addOption(option);
        assertTrue(commandLine.hasOption(option));
    }

    @Test
    public void testHasOption_NullOption() {
        assertFalse(commandLine.hasOption(null));
    }
}
