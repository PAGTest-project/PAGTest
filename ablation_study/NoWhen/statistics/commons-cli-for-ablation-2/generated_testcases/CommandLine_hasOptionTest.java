
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandLine_hasOptionTest {
    private CommandLine commandLine;

    @BeforeEach
    public void setUp() {
        commandLine = new CommandLine();
        commandLine.addOption(Option.builder("a").build());
        commandLine.addOption(Option.builder("b").hasArg().build());
    }

    @Test
    public void testHasOptionPresent() {
        assertTrue(commandLine.hasOption("a"));
    }

    @Test
    public void testHasOptionAbsent() {
        assertFalse(commandLine.hasOption("c"));
    }

    @Test
    public void testHasOptionDeprecated() {
        Option deprecatedOption = Option.builder("d").hasArg().deprecated().build();
        commandLine.addOption(deprecatedOption);
        assertTrue(commandLine.hasOption("d"));
    }
}
