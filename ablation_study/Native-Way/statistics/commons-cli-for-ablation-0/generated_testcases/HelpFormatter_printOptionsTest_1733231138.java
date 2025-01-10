
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpFormatter_printOptionsTest {
    private HelpFormatter formatter;
    private StringWriter sw;

    @BeforeEach
    public void setUp() {
        formatter = new HelpFormatter();
        sw = new StringWriter();
    }

    @Test
    public void testPrintOptions() {
        Options options = new Options();
        options.addOption("f", "file", true, "The file to be processed");
        options.addOption("v", "version", false, "Print the version of the application");
        options.addOption("h", "help", false, "Print this help message");

        formatter.printOptions(new PrintWriter(sw), HelpFormatter.DEFAULT_WIDTH, options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD);

        String expected = " -f,--file <arg>   The file to be processed\n" +
                          " -h,--help         Print this help message\n" +
                          " -v,--version      Print the version of the application\n";

        assertEquals(expected, sw.toString());
    }

    @Test
    public void testPrintOptionsWithEmptyOptions() {
        Options options = new Options();

        formatter.printOptions(new PrintWriter(sw), HelpFormatter.DEFAULT_WIDTH, options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD);

        assertEquals("", sw.toString());
    }
}
