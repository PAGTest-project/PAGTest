
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.PrintWriter;
import java.io.StringWriter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpFormatter_printHelpTest {

    private HelpFormatter formatter;
    private StringWriter stringWriter;
    private PrintWriter printWriter;

    @BeforeEach
    public void setUp() {
        formatter = new HelpFormatter();
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
    }

    @Test
    public void testPrintHelpWithHeaderAndFooter() {
        Options options = new Options();
        options.addOption(Option.builder("f").longOpt("file").hasArg().argName("FILE").desc("The file to be processed").build());
        options.addOption(Option.builder("v").longOpt("version").desc("Print the version of the application").build());
        options.addOption(Option.builder("h").longOpt("help").desc("Print this help message").build());

        String header = "Do something useful with an input file\n\n";
        String footer = "\nPlease report issues at https://example.com/issues";

        formatter.printHelp(printWriter, 80, "myapp", header, options, 1, 3, footer, true);
        printWriter.flush();

        String expectedOutput = "usage: myapp -f <FILE> [-h] [-v]\n" +
                                "Do something useful with an input file\n\n" +
                                " -f,--file <FILE>   The file to be processed\n" +
                                " -h,--help          Print this help message\n" +
                                " -v,--version       Print the version of the application\n\n" +
                                "Please report issues at https://example.com/issues\n";

        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    public void testPrintHelpWithoutHeaderAndFooter() {
        Options options = new Options();
        options.addOption(Option.builder("f").longOpt("file").hasArg().argName("FILE").desc("The file to be processed").build());
        options.addOption(Option.builder("v").longOpt("version").desc("Print the version of the application").build());
        options.addOption(Option.builder("h").longOpt("help").desc("Print this help message").build());

        formatter.printHelp(printWriter, 80, "myapp", null, options, 1, 3, null, true);
        printWriter.flush();

        String expectedOutput = "usage: myapp -f <FILE> [-h] [-v]\n" +
                                " -f,--file <FILE>   The file to be processed\n" +
                                " -h,--help          Print this help message\n" +
                                " -v,--version       Print the version of the application\n";

        assertEquals(expectedOutput, stringWriter.toString());
    }
}
