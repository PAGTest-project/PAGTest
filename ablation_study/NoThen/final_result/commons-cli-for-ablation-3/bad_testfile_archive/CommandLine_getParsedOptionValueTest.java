
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
        option = Option.builder("o").longOpt("option").hasArg().build();
    }

    @Test
    public void testGetParsedOptionValueWithNullOption() {
        Supplier<String> defaultValue = () -> "default";
        String result = commandLine.getParsedOptionValue(null, defaultValue);
        assertEquals("default", result);
    }

    @Test
    public void testGetParsedOptionValueWithNonNullOptionAndNullResult() {
        Supplier<String> defaultValue = () -> "default";
        String result = commandLine.getParsedOptionValue(option, defaultValue);
        assertEquals("default", result);
    }

    @Test
    public void testGetParsedOptionValueWithNonNullOptionAndNonNullResult() {
        option.getValuesList().add("value");
        Supplier<String> defaultValue = () -> "default";
        String result = commandLine.getParsedOptionValue(option, defaultValue);
        assertEquals("value", result);
    }

    @Test
    public void testGetParsedOptionValueWithConverterException() {
        option.setConverter(s -> {
            throw new RuntimeException("Converter error");
        });
        Supplier<String> defaultValue = () -> "default";
        assertThrows(ParseException.class, () -> commandLine.getParsedOptionValue(option, defaultValue));
    }
}
