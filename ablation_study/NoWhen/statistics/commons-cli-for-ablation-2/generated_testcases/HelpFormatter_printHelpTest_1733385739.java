
package org.apache.commons.cli;

import org.junit.jupiter.api.Test;
import java.io.PrintWriter;
import java.io.StringWriter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpFormatter_printHelpTest {

    @Test
    public void testPrintHelp() {
        // Given
        HelpFormatter formatter = new HelpFormatter();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        int width = 80;
        String cmdLineSyntax = "myapp";
        String header = "Header text";
        Options options = new Options();
        String footer = "Footer text";
        boolean autoUsage = true;

        // When
        formatter.printHelp(pw, width, cmdLineSyntax, header, options, footer, autoUsage);
        pw.flush();

        // Then
        String expectedOutput = "usage: myapp\nHeader text\n\nFooter text\n";
        assertEquals(expectedOutput, sw.toString().trim());
    }
}
