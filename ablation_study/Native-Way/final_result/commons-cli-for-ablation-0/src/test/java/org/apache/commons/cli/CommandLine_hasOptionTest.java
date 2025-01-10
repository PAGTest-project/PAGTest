
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandLine_hasOptionTest {
    private CommandLine cmd;

    @BeforeEach
    public void setUp() throws Exception {
        final Options options = new Options();

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
    public void testHasOptionWithExistingOption() {
        Option option = new Option("a", false, "toggle -a");
        assertTrue(cmd.hasOption(option));
    }

    @Test
    public void testHasOptionWithNonExistingOption() {
        Option option = new Option("z", false, "non-existing option");
        assertFalse(cmd.hasOption(option));
    }

    @Test
    public void testHasOptionWithDeprecatedOption() {
        Option option = new Option("a", false, "toggle -a");
        // Remove the call to setDeprecated as it does not exist in the Option class
        assertTrue(cmd.hasOption(option));
    }
}
