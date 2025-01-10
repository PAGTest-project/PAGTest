
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandLine_getOptionObjectTest {
    private CommandLine commandLine;

    @BeforeEach
    public void setUp() {
        commandLine = new CommandLine();
        commandLine.addOption(new Option("a", "optionA", true, "Option A"));
    }

    @Test
    public void testGetOptionObjectSuccess() {
        Object optionObject = commandLine.getOptionObject('a');
        assertNotNull(optionObject, "Option object should not be null");
        assertEquals("Option A", optionObject, "Option object should match the description");
    }

    @Test
    public void testGetOptionObjectFailure() {
        Object optionObject = commandLine.getOptionObject('b');
        assertNull(optionObject, "Option object should be null for non-existent option");
    }
}
