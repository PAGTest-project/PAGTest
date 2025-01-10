
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandLine_getOptionObjectTest {

    private CommandLine commandLine;

    @BeforeEach
    public void setUp() {
        commandLine = new CommandLine();
    }

    @Test
    public void testGetOptionObjectSuccess() throws ParseException {
        Option option = Option.builder("i").hasArg().type(Number.class).build();
        commandLine.addOption(option);
        commandLine.addArg("-i");
        commandLine.addArg("123");

        Object result = commandLine.getOptionObject("i");
        assertEquals(123, result);
    }

    @Test
    public void testGetOptionObjectParseException() {
        Option option = Option.builder("i").hasArg().type(Number.class).build();
        commandLine.addOption(option);
        commandLine.addArg("-i");
        commandLine.addArg("foo");

        Object result = commandLine.getOptionObject("i");
        assertNull(result);
    }

    @Test
    public void testGetOptionObjectNullOption() {
        Object result = commandLine.getOptionObject("nonexistent");
        assertNull(result);
    }
}
