
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Supplier;

public class CommandLine_getParsedOptionValueTest {
    private CommandLine commandLine;
    private Option option;

    @BeforeEach
    public void setUp() {
        commandLine = new CommandLine();
        option = new Option("o", "option", true, "option with argument");
        commandLine.addOption(option);
    }

    @Test
    public void testGetParsedOptionValueWithNullOption() {
        Supplier<String> defaultValue = () -> "default";
        String result = commandLine.getParsedOptionValue(null, defaultValue);
        assertEquals("default", result);
    }

    @Test
    public void testGetParsedOptionValueWithValidOption() {
        Supplier<String> defaultValue = () -> "default";
        String result = commandLine.getParsedOptionValue(option, defaultValue);
        assertEquals("default", result);
    }

    @Test
    public void testGetParsedOptionValueWithInvalidOption() {
        Option invalidOption = new Option("i", "invalid", true, "invalid option");
        Supplier<String> defaultValue = () -> "default";
        String result = commandLine.getParsedOptionValue(invalidOption, defaultValue);
        assertEquals("default", result);
    }

    @Test
    public void testGetParsedOptionValueWithConverter() {
        option.setConverter(s -> Integer.valueOf(s));
        Supplier<Integer> defaultValue = () -> 0;
        Option newOption = new Option("o", "option", true, "option with argument");
        newOption.setValues(new String[]{"123"});
        commandLine.addOption(newOption);
        Integer result = commandLine.getParsedOptionValue(option, defaultValue);
        assertEquals(123, result);
    }

    @Test
    public void testGetParsedOptionValueWithConverterException() {
        option.setConverter(s -> {
            throw new NumberFormatException("Invalid number");
        });
        Supplier<Integer> defaultValue = () -> 0;
        Option newOption = new Option("o", "option", true, "option with argument");
        newOption.setValues(new String[]{"abc"});
        commandLine.addOption(newOption);
        assertThrows(ParseException.class, () -> commandLine.getParsedOptionValue(option, defaultValue));
    }
}
