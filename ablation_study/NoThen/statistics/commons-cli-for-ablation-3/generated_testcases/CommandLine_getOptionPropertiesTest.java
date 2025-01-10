
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandLine_getOptionPropertiesTest {

    private CommandLine commandLine;
    private Option option;

    @BeforeEach
    public void setUp() {
        commandLine = new CommandLine();
        option = new Option("testOpt", "Test Option", true, "This is a test option");
        option.setArgs(2);
        option.addValue("key1");
        option.addValue("value1");
        option.addValue("key2");
        option.addValue("value2");
        commandLine.addOption(option);
    }

    @Test
    public void testGetOptionPropertiesWithExistingOption() {
        Properties props = commandLine.getOptionProperties(option);
        assertEquals(2, props.size());
        assertEquals("value1", props.getProperty("key1"));
        assertEquals("value2", props.getProperty("key2"));
    }

    @Test
    public void testGetOptionPropertiesWithNonExistingOption() {
        Option nonExistingOption = new Option("nonExistingOpt", "Non-existing Option", true, "This option does not exist");
        Properties props = commandLine.getOptionProperties(nonExistingOption);
        assertTrue(props.isEmpty());
    }

    @Test
    public void testGetOptionPropertiesWithEmptyValues() {
        Option emptyValuesOption = new Option("emptyValuesOpt", "Empty Values Option", true, "This option has empty values");
        emptyValuesOption.setArgs(2);
        commandLine.addOption(emptyValuesOption);
        Properties props = commandLine.getOptionProperties(emptyValuesOption);
        assertTrue(props.isEmpty());
    }

    @Test
    public void testGetOptionPropertiesWithOddNumberOfValues() {
        Option oddValuesOption = new Option("oddValuesOpt", "Odd Values Option", true, "This option has an odd number of values");
        oddValuesOption.setArgs(3);
        oddValuesOption.addValue("key1");
        oddValuesOption.addValue("value1");
        oddValuesOption.addValue("key2");
        commandLine.addOption(oddValuesOption);
        Properties props = commandLine.getOptionProperties(oddValuesOption);
        assertEquals(2, props.size());
        assertEquals("value1", props.getProperty("key1"));
        assertEquals("true", props.getProperty("key2"));
    }

    @Test
    public void testGetOptionPropertiesWithNullOption() {
        Properties props = commandLine.getOptionProperties((Option) null);
        assertTrue(props.isEmpty());
    }
}
