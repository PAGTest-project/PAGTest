
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
        Option option = Option.builder("o").longOpt("option").hasArg().build();
        List<String> values = new ArrayList<>();
        values.add("key1");
        values.add("value1");
        option.getValuesList().addAll(values);
        commandLine.addOption(option);

        // When
        Properties props = commandLine.getOptionProperties(option);

        // Then
        assertEquals(1, props.size());
        assertEquals("value1", props.getProperty("key1"));
    }

    @Test
    public void testGetOptionProperties_OptionNotFound() {
        // Given
        Option option = Option.builder("o").longOpt("option").hasArg().build();
        Option otherOption = Option.builder("p").longOpt("otherOption").hasArg().build();
        List<String> values = new ArrayList<>();
        values.add("key2");
        values.add("value2");
        otherOption.getValuesList().addAll(values);
        commandLine.addOption(otherOption);

        // When
        Properties props = commandLine.getOptionProperties(option);

        // Then
        assertTrue(props.isEmpty());
    }

    @Test
    public void testGetOptionProperties_MultipleValues() {
        // Given
        Option option = Option.builder("o").longOpt("option").hasArg().build();
        List<String> values = new ArrayList<>();
        values.add("key1");
        values.add("value1");
        values.add("key2");
        values.add("value2");
        option.getValuesList().addAll(values);
        commandLine.addOption(option);

        // When
        Properties props = commandLine.getOptionProperties(option);

        // Then
        assertEquals(2, props.size());
        assertEquals("value1", props.getProperty("key1"));
        assertEquals("value2", props.getProperty("key2"));
    }

    @Test
    public void testGetOptionProperties_OddNumberOfValues() {
        // Given
        Option option = Option.builder("o").longOpt("option").hasArg().build();
        List<String> values = new ArrayList<>();
        values.add("key1");
        values.add("value1");
        values.add("key2");
        option.getValuesList().addAll(values);
        commandLine.addOption(option);

        // When
        Properties props = commandLine.getOptionProperties(option);

        // Then
        assertEquals(2, props.size());
        assertEquals("value1", props.getProperty("key1"));
        assertEquals("true", props.getProperty("key2"));
    }
}
