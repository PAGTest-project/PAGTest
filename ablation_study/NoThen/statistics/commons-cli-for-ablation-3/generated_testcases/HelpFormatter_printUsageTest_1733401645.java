
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
        // Given
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        options.addOption("f", "file", true, "The file to be processed");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        // When
        formatter.printUsage(pw, 80, "myapp", options);
        pw.flush();

        // Then
        String expected = "usage: myapp -f <FILE>\n";
        assertEquals(expected, sw.toString());
    }

    @Test
    public void testPrintUsageWithOptionGroup() {
        // Given
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", "optionA", false, "Option A"));
        group.addOption(new Option("b", "optionB", false, "Option B"));
        options.addOptionGroup(group);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        // When
        formatter.printUsage(pw, 80, "myapp", options);
        pw.flush();

        // Then
        String expected = "usage: myapp [-a | -b]\n";
        assertEquals(expected, sw.toString());
    }

    @Test
    public void testPrintUsageWithOptionComparator() {
        // Given
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();
        options.addOption("b", "optionB", false, "Option B");
        options.addOption("a", "optionA", false, "Option A");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        // When
        formatter.printUsage(pw, 80, "myapp", options);
        pw.flush();

        // Then
        String expected = "usage: myapp -a -b\n";
        assertEquals(expected, sw.toString());
    }
}
