
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
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
    public void testGetOptionPropertiesWithExistingOption() {
        Option option = new Option("opt", "option", true, "description");
        option.setValuesList(List.of("key1", "value1", "key2", "value2"));

        commandLine.addOption(option);

        Properties props = commandLine.getOptionProperties(option);

        assertNotNull(props);
        assertEquals("value1", props.getProperty("key1"));
        assertEquals("value2", props.getProperty("key2"));
    }

    @Test
    public void testGetOptionPropertiesWithNonExistingOption() {
        Option option = new Option("opt", "option", true, "description");
        option.setValuesList(List.of("key1", "value1"));

        Properties props = commandLine.getOptionProperties(option);

        assertNotNull(props);
        assertTrue(props.isEmpty());
    }

    @Test
    public void testGetOptionPropertiesWithEmptyValues() {
        Option option = new Option("opt", "option", true, "description");
        commandLine.addOption(option);

        Properties props = commandLine.getOptionProperties(option);

        assertNotNull(props);
        assertTrue(props.isEmpty());
    }

    @Test
    public void testGetOptionPropertiesWithOddNumberOfValues() {
        Option option = new Option("opt", "option", true, "description");
        option.setValuesList(List.of("key1", "value1", "key2"));

        commandLine.addOption(option);

        Properties props = commandLine.getOptionProperties(option);

        assertNotNull(props);
        assertEquals("value1", props.getProperty("key1"));
        assertEquals("true", props.getProperty("key2"));
    }
}
