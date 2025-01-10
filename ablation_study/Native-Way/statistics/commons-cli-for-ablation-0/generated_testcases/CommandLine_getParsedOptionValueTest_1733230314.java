
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
        option = Option.builder("test").hasArg().build();
    }

    @Test
    public void testGetParsedOptionValueWithNullOption() throws ParseException {
        Supplier<String> defaultValue = () -> "default";
        String result = commandLine.getParsedOptionValue((Option) null, defaultValue);
        assertEquals("default", result);
    }

    @Test
    public void testGetParsedOptionValueWithNullResult() throws ParseException {
        Supplier<String> defaultValue = () -> "default";
        String result = commandLine.getParsedOptionValue(option, defaultValue);
        assertEquals("default", result);
    }

    @Test
    public void testGetParsedOptionValueWithValidResult() throws ParseException {
        option.setValue("123");
        Supplier<Integer> defaultValue = () -> 0;
        Integer result = commandLine.getParsedOptionValue(option, defaultValue);
        assertEquals(123, result);
    }

    @Test
    public void testGetParsedOptionValueWithException() {
        option.setValue("invalid");
        Supplier<Integer> defaultValue = () -> 0;
        assertThrows(ParseException.class, () -> {
            commandLine.getParsedOptionValue(option, defaultValue);
        });
    }
}
