
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandLine_getOptionValueTest {
    private CommandLine commandLine;

    @BeforeEach
    public void setUp() {
        commandLine = new CommandLine();
    }

    @Test
    public void testGetOptionValueWithNullValues() {
        Option option = new Option("test", "test option");
        commandLine.addOption(option);
        assertNull(commandLine.getOptionValue(option));
    }

    @Test
    public void testGetOptionValueWithNonNullValues() {
        Option option = new Option("test", "test option");
        option.setValues("value1");
        commandLine.addOption(option);
        assertEquals("value1", commandLine.getOptionValue(option));
    }
}
