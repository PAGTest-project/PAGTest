
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

        options.addOption(Option.builder("e").hasArgs().desc("set -e ").build());
        options.addOption("f", "f", false, "jk");
        options.addOption(Option.builder("g").hasArgs(2).desc("set -g").build());
        options.addOption(Option.builder("h").hasArg().desc("set -h").build());
        options.addOption(Option.builder("i").desc("set -i").build());
        options.addOption(Option.builder("j").hasArgs().desc("set -j").valueSeparator('=').build());
        options.addOption(Option.builder("k").hasArgs().desc("set -k").valueSeparator('=').build());
        options.addOption(Option.builder("m").hasArgs().desc("set -m").valueSeparator().build());

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

        final CommandLineParser parser = new DefaultParser();

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
        assertTrue(cmd.hasOption(deprecatedOption), "Deprecated option dep should be present");
    }
}
