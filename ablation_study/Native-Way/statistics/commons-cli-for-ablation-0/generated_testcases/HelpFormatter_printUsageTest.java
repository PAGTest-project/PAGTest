
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpFormatter_printUsageTest {
    private HelpFormatter formatter;
    private StringWriter sw;

    @BeforeEach
    public void setUp() {
        formatter = new HelpFormatter();
        sw = new StringWriter();
    }

    @Test
    public void testPrintUsage() {
        Options options = new Options();
        options.addOption(Option.builder("f").longOpt("file").hasArg().argName("file").desc("The file to be processed").build());
        options.addOption("v", "version", false, "Print the version of the application");
        options.addOption("h", "help", false, "Print this help message");

        formatter.printUsage(new PrintWriter(sw), HelpFormatter.DEFAULT_WIDTH, "myapp", options);

        String expected = "usage: myapp [-f <file>] [-h] [-v]\n";
        assertEquals(expected, sw.toString());
    }

    @Test
    public void testPrintUsageWithOptionGroup() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", "optionA", false, "Option A"));
        group.addOption(new Option("b", "optionB", false, "Option B"));
        options.addOptionGroup(group);

        formatter.printUsage(new PrintWriter(sw), HelpFormatter.DEFAULT_WIDTH, "myapp", options);

        String expected = "usage: myapp [-a | -b]\n";
        assertEquals(expected, sw.toString());
    }

    @Test
    public void testPrintUsageWithRequiredOption() {
        Options options = new Options();
        options.addOption(Option.builder("f").longOpt("file").hasArg().argName("file").desc("The file to be processed").required().build());

        formatter.printUsage(new PrintWriter(sw), HelpFormatter.DEFAULT_WIDTH, "myapp", options);

        String expected = "usage: myapp -f <file>\n";
        assertEquals(expected, sw.toString());
    }

    @Test
    public void testPrintUsageWithNoOptions() {
        Options options = new Options();

        formatter.printUsage(new PrintWriter(sw), HelpFormatter.DEFAULT_WIDTH, "myapp", options);

        String expected = "usage: myapp\n";
        assertEquals(expected, sw.toString());
    }
}
