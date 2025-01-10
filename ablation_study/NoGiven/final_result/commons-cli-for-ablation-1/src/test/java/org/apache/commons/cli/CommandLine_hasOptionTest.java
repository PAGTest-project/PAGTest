
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandLine_hasOptionTest {
    private CommandLine commandLine;
    private Option option;

    @BeforeEach
    public void setUp() {
        commandLine = new CommandLine();
        option = Option.builder("o").longOpt("option").hasArg().build();
    }

    @Test
    public void testHasOptionPresent() {
        commandLine.addOption(option);
        assertTrue(commandLine.hasOption(option));
    }

    @Test
    public void testHasOptionAbsent() {
        assertFalse(commandLine.hasOption(option));
    }

    @Test
    public void testHasOptionDeprecated() {
        Option deprecatedOption = Option.builder("d").longOpt("deprecated").hasArg().deprecated().build();
        commandLine.addOption(deprecatedOption);
        assertTrue(commandLine.hasOption(deprecatedOption));
    }
}
