
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandLine_getOptionPropertiesTest {

    private CommandLine commandLine;

    @BeforeEach
    public void setUp() {
        commandLine = new CommandLine();
    }

    @Test
    public void testGetOptionProperties_OptionFound() {
        // Given
        Option option = Option.builder("D").numberOfArgs(2).valueSeparator('=').build();
        option.addValue("param1=value1");
        option.addValue("param2=value2");
        commandLine.addOption(option);

        // When
        Properties props = commandLine.getOptionProperties(option);

        // Then
        assertEquals(2, props.size());
        assertEquals("value1", props.getProperty("param1"));
        assertEquals("value2", props.getProperty("param2"));
    }

    @Test
    public void testGetOptionProperties_OptionNotFound() {
        // Given
        Option option = Option.builder("D").numberOfArgs(2).valueSeparator('=').build();
        commandLine.addOption(option);

        Option nonExistentOption = Option.builder("E").numberOfArgs(2).valueSeparator('=').build();

        // When
        Properties props = commandLine.getOptionProperties(nonExistentOption);

        // Then
        assertTrue(props.isEmpty());
    }

    @Test
    public void testGetOptionProperties_OptionWithSingleValue() {
        // Given
        Option option = Option.builder("D").numberOfArgs(1).build();
        option.addValue("param1");
        commandLine.addOption(option);

        // When
        Properties props = commandLine.getOptionProperties(option);

        // Then
        assertEquals(1, props.size());
        assertEquals("true", props.getProperty("param1"));
    }
}
