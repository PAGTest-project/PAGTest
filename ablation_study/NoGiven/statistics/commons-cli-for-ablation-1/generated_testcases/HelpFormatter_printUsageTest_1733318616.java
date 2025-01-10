
package org.apache.commons.cli;

import org.junit.jupiter.api.Test;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpFormatter_printUsageTest {

    @Test
    public void testPrintUsage_NoOptionGroups() {
        HelpFormatter formatter = new HelpFormatter();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        Options options = new Options();
        options.addOption("a", "optionA", false, "Description for A");
        options.addOption("b", "optionB", true, "Description for B");

        formatter.printUsage(pw, 80, "myapp", options);
        pw.flush();

        String expected = "usage: myapp -a [-b <arg>]\n";
        assertEquals(expected, sw.toString());
    }

    @Test
    public void testPrintUsage_WithOptionGroups() {
        HelpFormatter formatter = new HelpFormatter();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", "optionA", false, "Description for A"));
        group.addOption(new Option("b", "optionB", true, "Description for B"));
        options.addOptionGroup(group);

        formatter.printUsage(pw, 80, "myapp", options);
        pw.flush();

        String expected = "usage: myapp -a | -b <arg>\n";
        assertEquals(expected, sw.toString());
    }

    @Test
    public void testPrintUsage_WithOptionComparator() {
        HelpFormatter formatter = new HelpFormatter();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        Options options = new Options();
        options.addOption("b", "optionB", true, "Description for B");
        options.addOption("a", "optionA", false, "Description for A");

        formatter.setOptionComparator((o1, o2) -> o1.getKey().compareTo(o2.getKey()));
        formatter.printUsage(pw, 80, "myapp", options);
        pw.flush();

        String expected = "usage: myapp -a [-b <arg>]\n";
        assertEquals(expected, sw.toString());
    }
}
