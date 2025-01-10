
package org.apache.commons.cli;

import org.junit.jupiter.api.Test;
import java.io.PrintWriter;
import java.io.StringWriter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpFormatter_printHelpTest {

    @Test
    public void testPrintHelp() {
        // Given
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        HelpFormatter formatter = new HelpFormatter();

        Options options = new Options();
        options.addOption("f", "file", true, "The file to be processed");
        options.addOption("v", "version", false, "Print the version of the application");
        options.addOption("h", "help", false, "Show help");

        String cmdLineSyntax = "myapp";
        String header = "Do something useful with an input file\n\n";
        String footer = "\nPlease report issues at https://example.com/issues";

        // When
        formatter.printHelp(printWriter, 80, cmdLineSyntax, header, options, footer, true);
        printWriter.flush();

        // Then
        String expectedOutput = "usage: myapp -f <FILE> [-h] [-v]\n" +
                                "Do something useful with an input file\n\n" +
                                " -f,--file <FILE>   The file to be processed\n" +
                                " -h,--help          Show help\n" +
                                " -v,--version       Print the version of the application\n" +
                                "\nPlease report issues at https://example.com/issues\n";
        assertEquals(expectedOutput, stringWriter.toString());
    }
}
