
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Properties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandLine_getOptionPropertiesTest {

    private CommandLine commandLine;

    @BeforeEach
    public void setUp() {
        commandLine = new CommandLine();
    }

    @Test
    public void testGetOptionProperties_OptionFound() {
        Option option = Option.builder("i").hasArg().build();
        option.addValue("value1");
        option.addValue("value2");
        commandLine.addOption(option);

        Properties props = commandLine.getOptionProperties(option);
        assertNotNull(props);
        assertEquals("value1", props.getProperty("i"));
        assertEquals("value2", props.getProperty("i.1"));
    }

    @Test
    public void testGetOptionProperties_OptionNotFound() {
        Option option = Option.builder("i").hasArg().build();
        Option nonExistentOption = Option.builder("x").hasArg().build();
        option.addValue("value1");
        commandLine.addOption(option);

        Properties props = commandLine.getOptionProperties(nonExistentOption);
        assertNotNull(props);
        assertTrue(props.isEmpty());
    }

    @Test
    public void testGetOptionProperties_NullOption() {
        Properties props = commandLine.getOptionProperties(null);
        assertNotNull(props);
        assertTrue(props.isEmpty());
    }
}
