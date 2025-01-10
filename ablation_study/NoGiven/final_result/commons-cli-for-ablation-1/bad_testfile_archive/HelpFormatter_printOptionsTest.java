
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpFormatter_printOptionsTest {
    private HelpFormatter helpFormatter;
    private StringWriter stringWriter;
    private PrintWriter printWriter;

    @BeforeEach
    public void setUp() {
        helpFormatter = new HelpFormatter();
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
    }

    @Test
    public void testPrintOptionsWithValidOptions() {
        Options options = new Options();
        options.addOption("a", "all", false, "find all matching manual pages.");
        options.addOption("d", "debug", false, "emit debugging messages.");

        helpFormatter.printOptions(printWriter, 80, options, 2, 20);
        printWriter.flush();

        String expected = "  -a,--all                  find all matching manual pages.\n" +
                          "  -d,--debug                emit debugging messages.\n";
        assertEquals(expected, stringWriter.toString());
    }

    @Test
    public void testPrintOptionsWithEmptyOptions() {
        Options options = new Options();

        helpFormatter.printOptions(printWriter, 80, options, 2, 2);
        printWriter.flush();

        assertEquals("", stringWriter.toString());
    }

    @Test
    public void testPrintOptionsWithNullOptions() {
        helpFormatter.printOptions(printWriter, 80, null, 2, 2);
        printWriter.flush();

        assertEquals("", stringWriter.toString());
    }
}
