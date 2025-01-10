
package org.apache.commons.cli;

import org.junit.jupiter.api.Test;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpFormatter_printUsageTest {

    @Test
    public void testPrintUsageWithSingleOption() {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        options.addOption("f", "file", true, "The file to be processed");

        StringWriter out = new StringWriter();
        PrintWriter pw = new PrintWriter(out);

        formatter.printUsage(pw, 80, "myapp", options);
        pw.flush();

        String expected = "usage: myapp -f <arg>\n";
        assertEquals(expected, out.toString());
    }

    @Test
    public void testPrintUsageWithOptionGroup() {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", "optionA", false, "Option A"));
        group.addOption(new Option("b", "optionB", false, "Option B"));
        options.addOptionGroup(group);

        StringWriter out = new StringWriter();
        PrintWriter pw = new PrintWriter(out);

        formatter.printUsage(pw, 80, "myapp", options);
        pw.flush();

        String expected = "usage: myapp [-a | -b]\n";
        assertEquals(expected, out.toString());
    }

    @Test
    public void testPrintUsageWithOptionComparator() {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        options.addOption("b", "optionB", false, "Option B");
        options.addOption("a", "optionA", false, "Option A");

        StringWriter out = new StringWriter();
        PrintWriter pw = new PrintWriter(out);

        formatter.printUsage(pw, 80, "myapp", options);
        pw.flush();

        String expected = "usage: myapp [-a] [-b]\n";
        assertEquals(expected, out.toString());
    }
}
