
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Supplier;

public class CommandLine_getParsedOptionValueTest {
    private CommandLine commandLine;
    private Options options;

    @BeforeEach
    public void setUp() throws Exception {
        options = new Options();
        options.addOption("a", true, "Option a with argument");
        options.addOption("b", false, "Option b without argument");

        final String[] args = {"-a", "valueA", "-b"};
        final CommandLineParser parser = new DefaultParser();
        commandLine = parser.parse(options, args);
    }

    @Test
    public void testGetParsedOptionValueWithValidOption() throws ParseException {
        Option optionA = options.getOption("a");
        Supplier<String> defaultValue = () -> "default";

        String result = commandLine.getParsedOptionValue(optionA, defaultValue);
        assertEquals("valueA", result);
    }

    @Test
    public void testGetParsedOptionValueWithNullOption() throws ParseException {
        Supplier<String> defaultValue = () -> "default";

        String result = commandLine.getParsedOptionValue(null, defaultValue);
        assertEquals("default", result);
    }

    @Test
    public void testGetParsedOptionValueWithInvalidOption() throws ParseException {
        Option optionC = new Option("c", true, "Option c with argument");
        Supplier<String> defaultValue = () -> "default";

        String result = commandLine.getParsedOptionValue(optionC, defaultValue);
        assertEquals("default", result);
    }

    @Test
    public void testGetParsedOptionValueWithConverterException() {
        Option optionA = options.getOption("a");
        optionA.setConverter(s -> { throw new RuntimeException("Converter error"); });
        Supplier<String> defaultValue = () -> "default";

        assertThrows(ParseException.class, () -> {
            commandLine.getParsedOptionValue(optionA, defaultValue);
        });
    }
}
