
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
        options.addOption(Option.builder("f").longOpt("file").hasArg().argName("FILE").desc("The file to be processed").build());
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
        group.addOption(Option.builder("a").longOpt("optionA").desc("Option A").build());
        group.addOption(Option.builder("b").longOpt("optionB").desc("Option B").build());
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
        options.addOption(Option.builder("b").longOpt("optionB").desc("Option B").build());
        options.addOption(Option.builder("a").longOpt("optionA").desc("Option A").build());
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
