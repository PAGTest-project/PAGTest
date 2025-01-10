
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandLine_getOptionObjectTest {
    private CommandLine commandLine;
    private Options options;

    @BeforeEach
    public void setUp() {
        options = new Options();
        options.addOption("a", true, "Option a with argument");
        options.addOption("b", false, "Option b without argument");
        commandLine = new CommandLine();
    }

    @Test
    public void testGetOptionObjectSuccess() {
        final String[] args = {"-a", "value"};
        CommandLineParser parser = new DefaultParser();
        try {
            commandLine = parser.parse(options, args);
            Object result = commandLine.getOptionObject("a");
            assertEquals("value", result);
        } catch (ParseException e) {
            fail("ParseException should not be thrown");
        }
    }

    @Test
    public void testGetOptionObjectFailure() {
        final String[] args = {"-a", "invalidValue"};
        CommandLineParser parser = new DefaultParser();
        try {
            commandLine = parser.parse(options, args);
            Object result = commandLine.getOptionObject("a");
            assertNull(result);
        } catch (ParseException e) {
            fail("ParseException should not be thrown");
        }
    }

    @Test
    public void testGetOptionObjectException() {
        final String[] args = {"-a", "exceptionValue"};
        CommandLineParser parser = new DefaultParser();
        try {
            commandLine = parser.parse(options, args);
            Object result = commandLine.getOptionObject("a");
            assertNull(result);
        } catch (ParseException e) {
            fail("ParseException should not be thrown");
        }
    }
}
