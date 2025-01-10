
package org.apache.commons.cli;

import org.junit.jupiter.api.Test;
import java.io.PrintWriter;
import java.io.StringWriter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpFormatter_printHelpTest {

    @Test
    public void testPrintHelp() {
        // Given
        int width = 80;
        String cmdLineSyntax = "myapp";
        String header = "Do something useful with an input file\n\n";
        Options options = new Options();
        options.addOption("f", "file", true, "The file to be processed");
        String footer = "\nPlease report issues at https://example.com/issues";
        boolean autoUsage = true;

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        HelpFormatter formatter = new HelpFormatter() {
            @Override
            protected PrintWriter getPrintWriter() {
                return printWriter;
            }
        };

        // When
        formatter.printHelp(width, cmdLineSyntax, header, options, footer, autoUsage);

        // Then
        String expectedOutput = "usage: myapp -f <FILE>\n" +
                                "Do something useful with an input file\n\n" +
                                " -f,--file <FILE>   The file to be processed\n" +
                                "\nPlease report issues at https://example.com/issues\n";
        assertEquals(expectedOutput, stringWriter.toString());
    }
}
