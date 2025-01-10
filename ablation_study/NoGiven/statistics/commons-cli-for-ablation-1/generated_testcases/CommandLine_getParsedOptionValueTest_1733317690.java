
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

        options.addOption(OptionBuilder.withLongOpt("e").hasArgs().withDescription("set -e ").create('e'));
        options.addOption("f", "f", false, "jk");
        options.addOption(OptionBuilder.withLongOpt("g").hasArgs(2).withDescription("set -g").create('g'));
        options.addOption(OptionBuilder.withLongOpt("h").hasArg().withDescription("set -h").create('h'));
        options.addOption(OptionBuilder.withLongOpt("i").withDescription("set -i").create('i'));
        options.addOption(OptionBuilder.withLongOpt("j").hasArgs().withDescription("set -j").withValueSeparator('=').create('j'));
        options.addOption(OptionBuilder.withLongOpt("k").hasArgs().withDescription("set -k").withValueSeparator('=').create('k'));
        options.addOption(OptionBuilder.withLongOpt("m").hasArgs().withDescription("set -m").withValueSeparator().create('m'));

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

        final CommandLineParser parser = new PosixParser();
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
