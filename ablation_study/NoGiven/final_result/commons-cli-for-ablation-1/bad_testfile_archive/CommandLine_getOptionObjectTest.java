
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandLine_getOptionObjectTest {
    private CommandLine commandLine;

    @BeforeEach
    public void setUp() {
        commandLine = new CommandLine();
    }

    @Test
    public void testGetOptionObjectSuccess() throws ParseException {
        // Given
        Option option = Option.builder("a").hasArg().build();
        commandLine.addOption(option);
        commandLine.addArg("value"); // Add an argument to the option

        // When
        Object result = commandLine.getParsedOptionValue("a");

        // Then
        assertNotNull(result);
    }

    @Test
    public void testGetOptionObjectParseException() {
        // Given
        Option option = Option.builder("b").hasArg().build();
        commandLine.addOption(option);

        // When
        Object result = commandLine.getOptionObject("b");

        // Then
        assertNull(result);
    }
}
