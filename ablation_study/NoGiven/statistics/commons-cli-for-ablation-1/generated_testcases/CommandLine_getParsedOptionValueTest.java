
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandLine_getParsedOptionValueTest {
    private CommandLine cmd;
    private Options options;

    @BeforeEach
    public void setUp() throws Exception {
        options = new Options();

        options.addOption("a", false, "toggle -a");
        options.addOption("b", true, "set -b");
        options.addOption("c", "c", false, "toggle -c");
        options.addOption("d", "d", true, "set -d");

        options.addOption(Option.builder("e").hasArgs().desc("set -e ").build());
        options.addOption("f", "f", false, "jk");
        options.addOption(Option.builder("g").hasArgs(2).desc("set -g").build());
        options.addOption(Option.builder("h").hasArg().desc("set -h").build());
        options.addOption(Option.builder("i").desc("set -i").build());
        options.addOption(Option.builder("j").hasArgs().desc("set -j").valueSeparator('=').build());
        options.addOption(Option.builder("k").hasArgs().desc("set -k").valueSeparator('=').build());
        options.addOption(Option.builder("m").hasArgs().desc("set -m").valueSeparator().build());

        final String[] args = {
            "-a",
            "-b", "foo",
            "--c",
            "--d", "bar",
            "-e", "one", "two",
            "-f",
            "arg1", "arg2",
            "-g", "val1", "val2", "arg3",
            "-h", "val1", "-i",
            "-h", "val2",
            "-jkey=value",
            "-j", "key=value",
            "-kkey1=value1",
            "-kkey2=value2",
            "-mkey=value"
        };

        final CommandLineParser parser = new DefaultParser();
        cmd = parser.parse(options, args);
    }

    @Test
    public void testGetParsedOptionValueSuccess() throws ParseException {
        final Option option = Option.builder("b").hasArg().build();
        final Supplier<String> defaultValue = () -> "default";

        final String result = cmd.getParsedOptionValue(option, defaultValue);
        assertEquals("foo", result);
    }

    @Test
    public void testGetParsedOptionValueNullOption() throws ParseException {
        final Supplier<String> defaultValue = () -> "default";

        final String result = cmd.getParsedOptionValue(null, defaultValue);
        assertEquals("default", result);
    }

    @Test
    public void testGetParsedOptionValueInvalidConverter() {
        final Option option = Option.builder("b").hasArg().build();
        option.setConverter(s -> { throw new RuntimeException("Converter error"); });
        final Supplier<String> defaultValue = () -> "default";

        assertThrows(ParseException.class, () -> {
            cmd.getParsedOptionValue(option, defaultValue);
        });
    }

    @Test
    public void testGetParsedOptionValueNoOptionValue() throws ParseException {
        final Option option = Option.builder("i").build();
        final Supplier<String> defaultValue = () -> "default";

        final String result = cmd.getParsedOptionValue(option, defaultValue);
        assertEquals("default", result);
    }
}
