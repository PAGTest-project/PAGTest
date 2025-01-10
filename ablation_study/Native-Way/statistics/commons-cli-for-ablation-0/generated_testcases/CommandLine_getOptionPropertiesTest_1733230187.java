
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandLine_getOptionPropertiesTest {

    private CommandLine commandLine;
    private List<Option> options;

    @BeforeEach
    public void setUp() {
        options = new ArrayList<>();
        commandLine = new CommandLine.Builder()
                .setDeprecatedHandler(CommandLine.Builder.DEPRECATED_HANDLER)
                .build();
    }

    @Test
    public void testGetOptionProperties_OptionFound() {
        Option option = Option.builder("D").numberOfArgs(2).valueSeparator('=').build();
        option.addValue("param1=value1");
        option.addValue("param2=value2");
        options.add(option);
        commandLine = new CommandLine.Builder()
                .addOption(option)
                .setDeprecatedHandler(CommandLine.Builder.DEPRECATED_HANDLER)
                .build();

        Properties props = commandLine.getOptionProperties("D");
        assertNotNull(props, "Properties should not be null");
        assertEquals(2, props.size(), "Number of properties");
        assertEquals("value1", props.getProperty("param1"), "Property 1");
        assertEquals("value2", props.getProperty("param2"), "Property 2");
    }

    @Test
    public void testGetOptionProperties_OptionNotFound() {
        Option option = Option.builder("D").numberOfArgs(2).valueSeparator('=').build();
        Option otherOption = Option.builder("E").numberOfArgs(2).valueSeparator('=').build();
        otherOption.addValue("param3=value3");
        options.add(otherOption);
        commandLine = new CommandLine.Builder()
                .addOption(otherOption)
                .setDeprecatedHandler(CommandLine.Builder.DEPRECATED_HANDLER)
                .build();

        Properties props = commandLine.getOptionProperties("D");
        assertNotNull(props, "Properties should not be null");
        assertEquals(0, props.size(), "Number of properties");
    }

    @Test
    public void testGetOptionProperties_OptionWithSingleValue() {
        Option option = Option.builder("D").numberOfArgs(1).build();
        option.addValue("param1");
        options.add(option);
        commandLine = new CommandLine.Builder()
                .addOption(option)
                .setDeprecatedHandler(CommandLine.Builder.DEPRECATED_HANDLER)
                .build();

        Properties props = commandLine.getOptionProperties("D");
        assertNotNull(props, "Properties should not be null");
        assertEquals(1, props.size(), "Number of properties");
        assertEquals("true", props.getProperty("param1"), "Property 1");
    }

    @Test
    public void testGetOptionProperties_OptionWithNoValues() {
        Option option = Option.builder("D").numberOfArgs(2).valueSeparator('=').build();
        options.add(option);
        commandLine = new CommandLine.Builder()
                .addOption(option)
                .setDeprecatedHandler(CommandLine.Builder.DEPRECATED_HANDLER)
                .build();

        Properties props = commandLine.getOptionProperties("D");
        assertNotNull(props, "Properties should not be null");
        assertEquals(0, props.size(), "Number of properties");
    }
}
