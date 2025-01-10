
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HelpFormatter_printUsageTest {
    private HelpFormatter formatter;
    private StringWriter sw;

    @BeforeEach
    public void setUp() {
        formatter = new HelpFormatter();
        sw = new StringWriter();
    }

    @Test
    public void testPrintUsageWithSingleOption() {
        final Options options = new Options();
        options.addOption("f", "file", true, "The file to be processed");

        formatter.printUsage(new PrintWriter(sw), 20, "app", options);

        final String expected = "usage: app -f <file>\n";
        assertEquals(expected, sw.toString());
    }

    @Test
    public void testPrintUsageWithMultipleOptions() {
        final Options options = new Options();
        options.addOption("f", "file", true, "The file to be processed");
        options.addOption("v", "version", false, "Print the version of the application");

        formatter.printUsage(new PrintWriter(sw), 20, "app", options);

        final String expected = "usage: app -f <file> [-v]\n";
        assertEquals(expected, sw.toString());
    }

    @Test
    public void testPrintUsageWithOptionGroup() {
        final Options options = new Options();
        final OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", "optionA", false, "Option A"));
        group.addOption(new Option("b", "optionB", false, "Option B"));
        options.addOptionGroup(group);

        formatter.printUsage(new PrintWriter(sw), 20, "app", options);

        final String expected = "usage: app [-a | -b]\n";
        assertEquals(expected, sw.toString());
    }

    @Test
    public void testPrintUsageWithRequiredOption() {
        final Options options = new Options();
        options.addOption(Option.builder("f").longOpt("file").hasArg().desc("The file to be processed").required().build());

        formatter.printUsage(new PrintWriter(sw), 20, "app", options);

        final String expected = "usage: app -f <file>\n";
        assertEquals(expected, sw.toString());
    }
}
