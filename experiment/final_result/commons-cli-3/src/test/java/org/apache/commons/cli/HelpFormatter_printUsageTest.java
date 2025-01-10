
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpFormatter_printUsageTest {

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
    public void testPrintUsage() {
        final String cmdLineSyntax = "myapp -f <FILE> [-h] [-v]";
        helpFormatter.printUsage(printWriter, 80, cmdLineSyntax);
        printWriter.flush();

        final String expected = "usage: myapp -f <FILE> [-h] [-v]\n";
        assertEquals(expected, stringWriter.toString());
    }
}
