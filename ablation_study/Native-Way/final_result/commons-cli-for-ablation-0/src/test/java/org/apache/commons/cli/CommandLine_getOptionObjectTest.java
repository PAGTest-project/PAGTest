
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
    public void testGetOptionObjectSuccess() {
        String opt = "validOption";
        // Mocking the behavior of getParsedOptionValue to return a non-null object
        commandLine = new CommandLine() {
            @Override
            public <T> T getParsedOptionValue(String opt) throws ParseException {
                return (T) new Object();
            }
        };
        assertNotNull(commandLine.getOptionObject(opt));
    }

    @Test
    public void testGetOptionObjectParseException() {
        String opt = "invalidOption";
        // Mocking the behavior of getParsedOptionValue to throw ParseException
        commandLine = new CommandLine() {
            @Override
            public <T> T getParsedOptionValue(String opt) throws ParseException {
                throw new ParseException("Invalid option");
            }
        };
        assertNull(commandLine.getOptionObject(opt));
    }
}
