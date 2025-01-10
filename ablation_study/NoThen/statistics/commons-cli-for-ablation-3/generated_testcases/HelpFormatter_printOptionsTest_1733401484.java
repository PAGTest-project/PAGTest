
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
        // Given
        Options options = new Options();
        options.addOption("f", "file", true, "The file to be processed");
        options.addOption("v", "version", false, "Print the version of the application");
        options.addOption("h", "help", false, "Print this help message");

        // When
        formatter.printOptions(new PrintWriter(sw), HelpFormatter.DEFAULT_WIDTH, options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD);

        // Then
        String expected = " -f,--file <arg>   The file to be processed\n" +
                          " -h,--help         Print this help message\n" +
                          " -v,--version      Print the version of the application\n";
        assertEquals(expected, sw.toString());
    }

    @Test
    public void testPrintOptionsWithEmptyOptions() {
        // Given
        Options options = new Options();

        // When
        formatter.printOptions(new PrintWriter(sw), HelpFormatter.DEFAULT_WIDTH, options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD);

        // Then
        assertEquals("", sw.toString());
    }

    @Test
    public void testPrintOptionsWithCustomPadding() {
        // Given
        Options options = new Options();
        options.addOption("f", "file", true, "The file to be processed");
        options.addOption("v", "version", false, "Print the version of the application");
        options.addOption("h", "help", false, "Print this help message");

        // When
        formatter.printOptions(new PrintWriter(sw), 50, options, 3, 5);

        // Then
        String expected = "   -f,--file <arg>      The file to be processed\n" +
                          "   -h,--help            Print this help message\n" +
                          "   -v,--version         Print the version of the application\n";
        assertEquals(expected, sw.toString());
    }
}
