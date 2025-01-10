
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public class HelpFormatter_printHelpTest {

    private HelpFormatter helpFormatter;
    private ByteArrayOutputStream outputStream;
    private PrintWriter printWriter;

    @BeforeEach
    public void setUp() {
        helpFormatter = new HelpFormatter();
        outputStream = new ByteArrayOutputStream();
        printWriter = new PrintWriter(outputStream);
    }

    @Test
    public void testPrintHelpWithHeaderAndFooter() {
        Options options = new Options();
        options.addOption("f", "file", true, "file to process");
        options.addOption("v", "verbose", false, "verbose output");

        String cmdLineSyntax = "myapp";
        String header = "Do something useful with an input file\n\n";
        String footer = "\nPlease report issues at https://example.com/issues";

        helpFormatter.printHelp(printWriter, HelpFormatter.DEFAULT_WIDTH, cmdLineSyntax, header, options, footer, true);
        printWriter.flush();

        String expectedOutput = "usage: myapp [-f <arg>] [-v]\n" +
                                "Do something useful with an input file\n\n" +
                                " -f,--file <arg>   file to process\n" +
                                " -v,--verbose      verbose output\n" +
                                "\nPlease report issues at https://example.com/issues\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintHelpWithoutHeaderAndFooter() {
        Options options = new Options();
        options.addOption("f", "file", true, "file to process");
        options.addOption("v", "verbose", false, "verbose output");

        String cmdLineSyntax = "myapp";

        helpFormatter.printHelp(printWriter, HelpFormatter.DEFAULT_WIDTH, cmdLineSyntax, null, options, null, false);
        printWriter.flush();

        String expectedOutput = "usage: myapp [-f <arg>] [-v]\n" +
                                " -f,--file <arg>   file to process\n" +
                                " -v,--verbose      verbose output\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintHelpWithAutoUsage() {
        Options options = new Options();
        options.addOption("f", "file", true, "file to process");
        options.addOption("v", "verbose", false, "verbose output");

        String cmdLineSyntax = "myapp";
        String header = "Do something useful with an input file\n\n";
        String footer = "\nPlease report issues at https://example.com/issues";

        helpFormatter.printHelp(printWriter, HelpFormatter.DEFAULT_WIDTH, cmdLineSyntax, header, options, footer, true);
        printWriter.flush();

        String expectedOutput = "usage: myapp [-f <arg>] [-v]\n" +
                                "Do something useful with an input file\n\n" +
                                " -f,--file <arg>   file to process\n" +
                                " -v,--verbose      verbose output\n" +
                                "\nPlease report issues at https://example.com/issues\n";

        assertEquals(expectedOutput, outputStream.toString());
    }
}
