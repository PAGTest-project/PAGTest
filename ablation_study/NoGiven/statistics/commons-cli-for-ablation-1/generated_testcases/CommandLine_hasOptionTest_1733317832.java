
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

        //@formatter:off
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
        //@formatter:on

        final CommandLineParser parser = new PosixParser();

        cmd = parser.parse(options, args);
    }

    @Test
    public void testHasOptionTrue() {
        assertTrue(cmd.hasOption("a"), "Option a should be present");
        assertTrue(cmd.hasOption("b"), "Option b should be present");
        assertTrue(cmd.hasOption("c"), "Option c should be present");
        assertTrue(cmd.hasOption("d"), "Option d should be present");
    }

    @Test
    public void testHasOptionFalse() {
        assertFalse(cmd.hasOption("z"), "Option z should not be present");
    }

    @Test
    public void testHasOptionDeprecated() {
        Option deprecatedOption = Option.builder("dep").longOpt("deprecated").hasArg().build();
        deprecatedOption.setDeprecated(true);
        cmd.addOption(deprecatedOption);
        assertTrue(cmd.hasOption("dep"), "Deprecated option dep should be present");
    }
}
